package org.confetti.rcp.views;

import static de.kupzog.ktable.renderers.DefaultCellRenderer.STYLE_PUSH;
import static java.util.stream.IntStream.range;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.SolutionSlot;
import org.confetti.core.StudentGroup;
import org.confetti.rcp.views.TimeTableModel.GetCellInfoVisitor;
import org.confetti.util.Tuple;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Lists;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableNoScrollModel;
import de.kupzog.ktable.renderers.DefaultCellRenderer;
import de.kupzog.ktable.renderers.FixedCellRenderer;

public class TimeTableColumnModel extends KTableNoScrollModel {

	//----------------------------- constants --------------------------------------------------------------------------
	private static final KTableCellRenderer RENDERER = new DefaultCellRenderer(STYLE_PUSH);
	private static final FixedCellRenderer FIXED_RENDERER = new FixedCellRenderer(STYLE_PUSH);

	//----------------------------- fields -----------------------------------------------------------------------------
	private final StudentGroup sg;
	private final int namesWidth;
	private final int namesHeight;
	private final List<Day> days;
	private final List<Hour> hours;
	
	private final Map<Point, Point> belongsTo = new HashMap<>();
	private final Map<Point, StudentGroup> headers = new HashMap<>();
	private final Map<Point, Assignment> assignments = new HashMap<>();
	
	//----------------------------- constructors -----------------------------------------------------------------------
	public TimeTableColumnModel(final KTable table, final DataProvider dp, final StudentGroup sg) {
		super(table);
		this.sg = sg;
		
		//Student Group Names header
		Map<Point, Tuple<Integer, StudentGroup>> widths = new HashMap<>();
		this.namesWidth = calcWidth(0, 0, sg, widths);
		this.namesHeight = calcHeight(sg) - 1;
		for (Map.Entry<Point, Tuple<Integer, StudentGroup>> entry : widths.entrySet()) {
			Point origPoint = entry.getKey();
			Point target = new Point(origPoint.x + getFixedHeaderColumnCount(), origPoint.y);
			
			range(0, entry.getValue().getFirst())
			.mapToObj(offset -> new Point(target.x + offset, target.y))
			.forEach(source  -> {
				this.belongsTo.put(source, target);
				this.headers.put(source, entry.getValue().getSecond());
			});
		}
		
		//Days and hours
		days = Lists.newArrayList(dp.getDays().getList());
		hours = Lists.newArrayList(dp.getHours().getList());

		//Assignments
		if (dp.getSolution().getValue() != null) {
			Map<Assignment, SolutionSlot> slotsByAssignment = new HashMap<>();
			dp.getSolution().getValue().forEach(slot -> slotsByAssignment.put(slot.getAssignment(), slot));

			StudentGroup studGroup = sg;
			while (studGroup != null) {
				addAssignments(studGroup, namesWidth, slotsByAssignment);
				studGroup = studGroup.getParent();
			}
		}
	}

	//----------------------------- KTableNoScrollModel's API ----------------------------------------------------------
	@Override public int getFixedHeaderColumnCount() 						{ return 2; }
	@Override public int getFixedHeaderRowCount() 							{ return namesHeight; }
	@Override public int getFixedSelectableColumnCount() 					{ return 0; }
	@Override public int getFixedSelectableRowCount() 						{ return 0; }

	@Override public int doGetColumnCount() 								{ return 2 + namesWidth;}
	@Override public int doGetRowCount() 									{ return namesHeight + days.size() * hours.size(); }
	
	@Override public int getInitialColumnWidth(int col) 					{ return col < 2 ? 10 : 60; }
	@Override public int getInitialRowHeight(int row) 						{ return 48; }
	@Override public int getRowHeightMinimum() 								{ return 24; }
	
	@Override public boolean isColumnResizable(int col) 					{ return false; }
	@Override public boolean isRowResizable(int row) 						{ return false; }

	@Override public KTableCellEditor doGetCellEditor(int arg0, int arg1) 	{ return null; }

	@Override
	public KTableCellRenderer doGetCellRenderer(int col, int row) {
		return (col >= getFixedHeaderColumnCount() && row >= getFixedRowCount()) ? RENDERER : FIXED_RENDERER;
	}
	
	@Override public void doSetContentAt(int arg0, int arg1, Object arg2) 	{ }

	@Override
	public Object doGetContentAt(int col, int row) {
		//top left blank corner
		if (col < getFixedHeaderColumnCount() && row < getFixedHeaderRowCount()) {
			return "";
		}
		//student group names
		if (col >= getFixedHeaderColumnCount() && row < getFixedHeaderRowCount()) {
			StudentGroup found = headers.get(new Point(col, row));
			if (found != null) {
				return found.getName().getValue();
			}
		}
		//days
		if (col == 0) {
			int absrow = row - getFixedHeaderRowCount();
			return days.get(absrow / hours.size()).getName().getValue();
		}
		//hours
		if (col == 1) {
			int absrow = row - getFixedHeaderRowCount();
			return hours.get(absrow % hours.size()).getName().getValue();
		}
		//assignments
		if (col >= getFixedHeaderColumnCount()) {
			Point point = new Point(col, row);
			if (assignments.containsKey(point)) {
				Assignment ass = assignments.get(point);
				return this.sg.accept(GetCellInfoVisitor.INSTANCE, ass);
			}
		}
		return "";
	}

	@Override
	public String doGetTooltipAt(int col, int row) {
		return (String) doGetContentAt(col, row);
	}
	
	@Override
	public Point belongsToCell(int col, int row) {
		//top left blank corner
		if (col < getFixedHeaderColumnCount() && row < getFixedHeaderRowCount()) {
			return new Point(0, 0);
		}
		//student group names
		if (col >= getFixedHeaderColumnCount() && row < getFixedHeaderRowCount()) {
			Point found = belongsTo.get(new Point(col, row));
			if (found != null) {
				return found;
			}
		}
		//days
		if (col == 0) {
			int absrow = row - getFixedHeaderRowCount();
			return new Point(0, getFixedHeaderRowCount() + (absrow / hours.size()) * hours.size());
		}
		//assignments
		if (col >= getFixedHeaderColumnCount()) {
			Point found = belongsTo.get(new Point(col, row));
			if (found != null) {
				return found;
			}
		}		
		return super.belongsToCell(col, row);
	}
	
	//----------------------------- helpers ----------------------------------------------------------------------------
	private static int calcWidth(int row, int col, StudentGroup sg, Map<Point, Tuple<Integer, StudentGroup>> spans) {
		int childWidth = 0;
		for (StudentGroup child : sg.getChildren().getList()) {
			int length = calcWidth(row + 1, col + childWidth, child, spans);
			spans.put(new Point(col + childWidth, row), new Tuple<>(length, child));
			childWidth += length;
		}
		return Math.max(1, childWidth);
	}

	private static int calcHeight(StudentGroup sg) {
		int childDepth = 0;
		for (StudentGroup child : sg.getChildren().getList()) {
			childDepth = Math.max(childDepth, calcHeight(child));
		}
		return 1 + childDepth;
	}

	private void addAssignments(StudentGroup sg, int sgWidth, Map<Assignment, SolutionSlot> slotsByAssg) {
		for (Assignment ass : sg.getAssignments().getList()) {
			if (!slotsByAssg.containsKey(ass)) {
				continue;
			}
			SolutionSlot foundSolutionSlot = slotsByAssg.get(ass);
			int day =  this.days.indexOf(foundSolutionSlot.getDay());
			int hour = this.hours.indexOf(foundSolutionSlot.getHour());
			
			Point point = new Point(getFixedHeaderColumnCount(), 
					getFixedHeaderRowCount() + day * hours.size() + hour);
			this.assignments.put(point, ass);
			
			range(0, sgWidth)
			.forEach(offset -> this.belongsTo.put(new Point(point.x + offset, point.y), point));
		}
	}

}
