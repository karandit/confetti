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
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.SolutionSlot;
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

	private static final String[] DEFAULT_DAYS = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	private static final String[] DEFAULT_HOURS = new String[] {"08 :00 - 08:45", "09 :00 - 09:45", "10 :00 - 10:45", "11 :00 - 11:45"};

	private final String[] days;
	private final String[] hours;
	private final Assignment[][] assignments;
	
	public static final KTableCellRenderer RENDERER = new DefaultCellRenderer(STYLE_PUSH);
	protected static final FixedCellRenderer FIXED_RENDERER = new FixedCellRenderer(STYLE_PUSH);
	
	public TimeTableModel(KTable table) {
		this(table, null, null);
	}

	public TimeTableModel(KTable table, DataProvider dp) {
		this(table, dp, null);
	}

	public TimeTableModel(KTable table, DataProvider dp, Entity entity) {
		super(table);
		if (dp == null) {
			this.days = DEFAULT_DAYS; 
			this.hours = DEFAULT_HOURS;
			this.assignments = new Assignment[days.length + 1][hours.length + 1];
 		} else {
 			this.days = toArray(getNames(dp.getDays())); 
 			this.hours = toArray(getNames(dp.getHours()));
			this.assignments = new Assignment[days.length + 1][hours.length + 1];
 			if (dp.getSolution().getValue() != null) {
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
						assignments[day + 1][hour + 1] = ass;
					}
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

	@Override public int getInitialColumnWidth(int col) 						{ return 60; }
	@Override public int getInitialRowHeight(int row) 							{ return row == 0 ? 24: 48; }
	@Override public int getRowHeightMinimum() 									{ return 48; }
	
	@Override public boolean isColumnResizable(int col) 						{ return false; }
	@Override public boolean isRowResizable(int row) 							{ return false; }

	@Override public KTableCellEditor doGetCellEditor(int arg0, int arg1) 		{ return null; }
	@Override public KTableCellRenderer doGetCellRenderer(int col, int row) 	{ return (row == 0 || col == 0) ? FIXED_RENDERER : RENDERER; }
	
	@Override public void doSetContentAt(int arg0, int arg1, Object arg2) 		{ } 
	@Override public Object doGetContentAt(int col, int row) { 
		switch (row) {
			case 0:	switch (col) {
				case 0: 	return "";
				default: 	return days[col -1];
			}
			default: switch (col) {
				case 0: return hours[row - 1];
				default: 
					if (assignments[col][row] instanceof Assignment) {
						Assignment ass = (Assignment) assignments[col][row];
						return ass.getSubject().getName().getValue() 
								+ "\n" + getNames(ass.getTeachers())
								+ "\n" + getNames(ass.getStudentGroups());
								
					} else {
						return "";
					}
			}
		}
	}
	
	private List<String> getNames(ObservableList<? extends Nameable> items) {
		List<String> names = new ArrayList<>();
		for (Nameable nameable : items.getList()) {
			names.add(nameable.getName().getValue());
		}
		return names;
	}
	
	private String[] toArray(List<String> names) {
		return names.toArray(new String[names.size()]);
	}
}
