package org.confetti.rcp.views;

import static de.kupzog.ktable.renderers.DefaultCellRenderer.STYLE_PUSH;

import java.util.ArrayList;
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

	private final String[] days;
	private final String[] hours;
	private final Entity entity;
	private final Assignment[][] assignments;
	
	private static final KTableCellRenderer RENDERER = new DefaultCellRenderer(STYLE_PUSH);
	private static final FixedCellRenderer FIXED_RENDERER = new FixedCellRenderer(STYLE_PUSH);
	
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
			Map<Assignment, SolutionSlot> assignmentSolutionSlot = new HashMap<>();
			for (SolutionSlot slot : dp.getSolution().getValue()) {
				assignmentSolutionSlot.put(slot.getAssignment(), slot);
			}
			ArrayList<Day> daysArr = Lists.newArrayList(dp.getDays().getList());
			ArrayList<Hour> hoursArr = Lists.newArrayList(dp.getHours().getList());
			
			for (Assignment ass : entity.getAssignments().getList()) {
				if (assignmentSolutionSlot.containsKey(ass)) {
					SolutionSlot foundSolutionSlot = assignmentSolutionSlot.get(ass);
					int day =  daysArr.indexOf(foundSolutionSlot.getDay());
					int hour = hoursArr.indexOf(foundSolutionSlot.getHour());
					this.assignments[day + 1][hour + 1] = ass;
				}
			}
		}
	}

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
				case 0: 	return "";
				default: 	return days[col - 1];
			}
			default: switch (col) {
				case 0: return hours[row - 1];
				default: 
					if (assignments[col][row] instanceof Assignment) {
						Assignment ass = (Assignment) assignments[col][row];
						return this.entity.accept(GetCellInfoVisitor.INSTANCE, ass);
					} else {
						return "";
					}
			}
		}
	}
	@Override
	public String doGetTooltipAt(int col, int row) {
		return (String) doGetContentAt(col, row);
	}
	
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
			return ass.getSubject().getName().getValue() + "\n" + getNames(ass.getStudentGroups());
		}
		@Override public String visitStudentGroup(StudentGroup studentGroup, Assignment ass) { 
			return ass.getSubject().getName().getValue() + "\n" + getNames(ass.getTeachers());
		}
		@Override public String visitRoom(Room room, Assignment ass) { return null; }
	}
	
}
