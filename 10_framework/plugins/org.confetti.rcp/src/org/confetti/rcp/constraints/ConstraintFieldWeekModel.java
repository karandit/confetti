package org.confetti.rcp.constraints;

import static de.kupzog.ktable.renderers.DefaultCellRenderer.STYLE_PUSH;

import java.util.ArrayList;
import java.util.List;

import org.confetti.core.DataProvider;
import org.confetti.core.Nameable;
import org.confetti.observable.ObservableList;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableNoScrollModel;
import de.kupzog.ktable.renderers.DefaultCellRenderer;
import de.kupzog.ktable.renderers.FixedCellRenderer;

public class ConstraintFieldWeekModel extends KTableNoScrollModel {

	private final String[] days;
	private final String[] hours;
	
	private static final KTableCellRenderer RENDERER = new DefaultCellRenderer(STYLE_PUSH);
	private static final FixedCellRenderer FIXED_RENDERER = new FixedCellRenderer(STYLE_PUSH);
	
	public ConstraintFieldWeekModel(KTable table, DataProvider dp) {
		super(table);
		this.days = toArray(getNames(dp.getDays())); 
		this.hours = toArray(getNames(dp.getHours()));
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
	@Override
	public Object doGetContentAt(int col, int row) { 
		switch (row) {
			case 0:	switch (col) {
				case 0: 	return "";
				default: 	return days[col - 1];
			}
			default: switch (col) {
				case 0: return hours[row - 1];
				default: return "X";
			}
		}
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
}
