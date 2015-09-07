package org.confetti.rcp.views;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Iterables.transform;
import static de.kupzog.ktable.renderers.DefaultCellRenderer.STYLE_PUSH;

import java.util.HashMap;
import java.util.Map;

import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.confetti.util.Tuple;
import org.eclipse.swt.graphics.Point;

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
//	private final DataProvider dp;
//	private final StudentGroup sg;
	private final int namesWidth;
	private final int namesHeight;
	private final String[] days;
	private final String[] hours;
	private final Map<Point, Tuple<Point, StudentGroup>> belongsTo = new HashMap<>();
	
	//----------------------------- constructors -----------------------------------------------------------------------
	public TimeTableColumnModel(KTable table, DataProvider dp, StudentGroup sg) {
		super(table);
//		this.dp = dp;
//		this.sg = sg;
		Map<Point, Tuple<Integer, StudentGroup>> widths = new HashMap<>();
		
		this.namesWidth = calcWidth(0, 0, sg, widths);
		this.namesHeight = calcHeight(sg) - 1;
		
		for (Map.Entry<Point, Tuple<Integer, StudentGroup>> entry : widths.entrySet()) {
			Point point = entry.getKey();
			Tuple<Point, StudentGroup> target = new Tuple<>(point, entry.getValue().getSecond());
			
			for (int i = 0; i < entry.getValue().getFirst(); i++) {
				Point source = new Point(point.x + i, point.y);
				belongsTo.put(source, target);
			}
		}
		days = toArray(transform(dp.getDays().getList(), x -> x.getName().getValue()), String.class);
		hours = toArray(transform(dp.getHours().getList(), x -> x.getName().getValue()), String.class);
	}
	
	//----------------------------- KTableNoScrollModel's API ----------------------------------------------------------
	@Override public int getFixedHeaderColumnCount() 						{ return 2; }
	@Override public int getFixedHeaderRowCount() 							{ return namesHeight; }
	@Override public int getFixedSelectableColumnCount() 					{ return 0; }
	@Override public int getFixedSelectableRowCount() 						{ return 0; }

	@Override public int doGetColumnCount() 								{ return 2 + namesWidth;}
	@Override public int doGetRowCount() 									{ return namesHeight + days.length * hours.length; }
	
	@Override public int getInitialColumnWidth(int col) 					{ return 60; }
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
			Tuple<Point, StudentGroup> found = belongsTo.get(new Point(col - getFixedHeaderColumnCount(), row));
			if (found != null) {
				return found.getSecond().getName().getValue();
			}
		}
		//days
		if (col == 0) {
			int absrow = row - getFixedHeaderRowCount();
			return days[absrow / hours.length];
		}
		//hours
		if (col == 1) {
			int absrow = row - getFixedHeaderRowCount();
			return hours[absrow % hours.length];
		}
		
		return "";
	}

	@Override
	public Point belongsToCell(int col, int row) {
		//top left blank corner
		if (col < getFixedHeaderColumnCount() && row < getFixedHeaderRowCount()) {
			return new Point(0, 0);
		}
		//student group names
		if (col >= getFixedHeaderColumnCount() && row < getFixedHeaderRowCount()) {
			Tuple<Point, StudentGroup> found = belongsTo.get(new Point(col - getFixedHeaderColumnCount(), row));
			if (found != null) {
				Point targetPoint = found.getFirst();
				return new Point(getFixedHeaderColumnCount() + targetPoint.x, targetPoint.y);
			}
		}
		//days
		if (col == 0) {
			int absrow = row - getFixedHeaderRowCount();
			return new Point(0, getFixedHeaderRowCount() + (absrow / hours.length) * hours.length);
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

}
