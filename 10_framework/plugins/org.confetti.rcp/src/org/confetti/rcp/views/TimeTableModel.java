package org.confetti.rcp.views;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static de.kupzog.ktable.renderers.DefaultCellRenderer.STYLE_PUSH;
import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Entity;
import org.confetti.core.EntityVisitor;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.Room;
import org.confetti.core.SolutionSlot;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.observable.ObservableList;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableNoScrollModel;
import de.kupzog.ktable.renderers.DefaultCellRenderer;
import de.kupzog.ktable.renderers.FixedCellRenderer;

/**
 * @author Gabor Bubla
 */
public class TimeTableModel extends KTableNoScrollModel {

	//----------------------------- constants --------------------------------------------------------------------------
	private static final KTableCellRenderer RENDERER = new DefaultCellRenderer(STYLE_PUSH);
	private static final FixedCellRenderer FIXED_RENDERER = new FixedCellRenderer(STYLE_PUSH);

	//----------------------------- fields -----------------------------------------------------------------------------
	private final String[] days;
	private final String[] hours;
	private final Entity entity;
	private final Assignment[][] assignments;
	private final Map<Point, Point> belongsTo = new HashMap<>();

	//----------------------------- constructors -----------------------------------------------------------------------
	public TimeTableModel(KTable table, DataProvider dp) {
		this(table, dp, null);
	}

	public TimeTableModel(KTable table, DataProvider dp, Entity entity) {
		super(table);
		this.days = toArray(getNames(dp.getDays())); 
		this.hours = toArray(getNames(dp.getHours()));
		this.entity = entity;
		this.assignments = new Assignment[days.length + 1][hours.length + 1];
		if (entity != null && dp.getSolution().getValue() != null) {
			ArrayList<Day> daysArr = Lists.newArrayList(dp.getDays().getList());
			ArrayList<Hour> hoursArr = Lists.newArrayList(dp.getHours().getList());
			
			Iterable<SolutionSlot> slots = entity.accept(GetSolutionSlotsVisitor.INSTANCE, dp.getSolution().getValue());
			slots.forEach(slot -> {
				int day =  daysArr.indexOf(slot.getDay());
				int hour = hoursArr.indexOf(slot.getHour());
				this.assignments[day + getFixedHeaderColumnCount()][hour + getFixedHeaderRowCount()] = slot.getAssignment();
				
				Point target = new Point(day + getFixedHeaderColumnCount(), hour + getFixedHeaderRowCount());
				
				range(0, slot.getAssignment().getDuration().getValue())
				.mapToObj(offset -> new Point(target.x, target.y + offset))
				.forEach(source  -> this.belongsTo.put(source, target));
			});
		}
	}

	//----------------------------- KTableNoScrollModel's API ----------------------------------------------------------
	@Override public int getFixedHeaderColumnCount() 							{ return 1; }
	@Override public int getFixedHeaderRowCount() 								{ return 1; }
	@Override public int getFixedSelectableColumnCount() 						{ return 0; }
	@Override public int getFixedSelectableRowCount() 							{ return 0; }

	@Override public int doGetColumnCount() 									{ return 1 + days.length; }
	@Override public int doGetRowCount() 										{ return 1 + hours.length; }

	@Override public int getInitialColumnWidth(int col) 						{ return col == 0 ? 10 : 60; }
	@Override public int getInitialRowHeight(int row) 							{ return row == 0 ? 24 : 48; }
	@Override public int getRowHeightMinimum() 									{ return 48; }
	
	@Override public boolean isColumnResizable(int col) 						{ return false; }
	@Override public boolean isRowResizable(int row) 							{ return false; }

	@Override public KTableCellEditor doGetCellEditor(int arg0, int arg1) 		{ return null; }

	@Override
	public KTableCellRenderer doGetCellRenderer(int col, int row) {
		if (row == 0 || col == 0) {
			return FIXED_RENDERER;
		}
		if (assignments[col][row] instanceof Assignment) {
			Assignment assignment = (Assignment) assignments[col][row];
			int color = assignment.getSubject().getColor();	
			return RendererCache.INSTANCE.getRenderer(color);
		}
		return RENDERER;
	}
	
	@Override public void doSetContentAt(int arg0, int arg1, Object arg2) 		{ }
	@Override
	public Object doGetContentAt(int col, int row) { 
		switch (row) {
			case 0:	switch (col) {
				case 0: 	return ""; //$NON-NLS-1$
				default: 	return days[col - 1];
			}
			default: switch (col) {
				case 0: return hours[row - 1];
				default: 
					if (assignments[col][row] instanceof Assignment) {
						Assignment ass = (Assignment) assignments[col][row];
						return this.entity.accept(GetCellInfoVisitor.INSTANCE, ass);
					} else {
						return ""; //$NON-NLS-1$
					}
			}
		}
	}
	@Override
	public String doGetTooltipAt(int col, int row) {
		return (String) doGetContentAt(col, row);
	}

	@Override
	public Point belongsToCell(int col, int row) {
		Point point = new Point(col, row);
		return belongsTo.getOrDefault(point, point);
	}

	//----------------------------- helpers ----------------------------------------------------------------------------
	private String[] toArray(List<String> names) {
		return names.toArray(new String[names.size()]);
	}
	
	private static List<String> getNames(ObservableList<? extends Nameable> items) {
		List<String> names = new ArrayList<>();
		for (Nameable nameable : items.getList()) {
			names.add(nameable.getName().getValue());
		}
		return names;
	}
	
	enum GetCellInfoVisitor implements EntityVisitor<String, Assignment> {
		INSTANCE;

		@Override public String visitSubject(Subject subject, Assignment ass) { return null; }
		@Override public String visitTeacher(Teacher teacher, Assignment ass) { 
			return ass.getSubject().getName().getValue() + "\n" + getNames(ass.getStudentGroups()); //$NON-NLS-1$
		}
		@Override public String visitStudentGroup(StudentGroup studentGroup, Assignment ass) { 
			return ass.getSubject().getName().getValue() + "\n" + getNames(ass.getTeachers()); //$NON-NLS-1$
		}
		@Override public String visitRoom(Room room, Assignment ass) { 
			return getNames(ass.getTeachers()) + "\n" + getNames(ass.getStudentGroups()); //$NON-NLS-1$
		}
	}
	
	enum GetSolutionSlotsVisitor implements EntityVisitor<Iterable<SolutionSlot>, Iterable<SolutionSlot>> {
		INSTANCE;

		@Override
		public Iterable<SolutionSlot> visitSubject(Subject subject, Iterable<SolutionSlot> slots) {
			return collectSlots(subject, createRepo(slots));
		}

		@Override
		public Iterable<SolutionSlot> visitTeacher(Teacher teacher, Iterable<SolutionSlot> slots) {
			return collectSlots(teacher, createRepo(slots));
		}

		@Override
		public Iterable<SolutionSlot> visitStudentGroup(StudentGroup studentGr, Iterable<SolutionSlot> slots) {
			Iterable<SolutionSlot> res = Arrays.asList();
			Map<Assignment, SolutionSlot> repo = createRepo(slots);
			StudentGroup sg = studentGr;
			while (sg != null) {
				res = Iterables.concat(res, collectSlots(sg, repo));
				sg = sg.getParent();
			}
			return res;
		}

		@Override
		public Iterable<SolutionSlot> visitRoom(Room room, Iterable<SolutionSlot> slots) {
			return filter(slots, slot -> room == slot.getRoom());
		}
		
		//------------------------ helpers -----------------------------------------------------------------------------
		private Map<Assignment, SolutionSlot> createRepo(Iterable<SolutionSlot> slots) {
			Map<Assignment, SolutionSlot> slotsByAssignment = new HashMap<>();
			for (SolutionSlot slot : slots) {
				slotsByAssignment.put(slot.getAssignment(), slot);
			}
			return slotsByAssignment;
		}
		
		private Iterable<SolutionSlot> collectSlots(Entity entity, Map<Assignment, SolutionSlot> slots) {
			return transform(
					filter(entity.getAssignments().getList(), ass -> slots.containsKey(ass)),
					ass -> slots.get(ass));
		}
	}
}
