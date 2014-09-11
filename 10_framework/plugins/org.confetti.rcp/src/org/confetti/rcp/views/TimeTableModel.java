package org.confetti.rcp.views;

import static de.kupzog.ktable.renderers.DefaultCellRenderer.STYLE_PUSH;
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
	
	public static final KTableCellRenderer RENDERER = new DefaultCellRenderer(STYLE_PUSH);
	protected static final FixedCellRenderer FIXED_RENDERER = new FixedCellRenderer(STYLE_PUSH);
	
	public TimeTableModel(KTable table) {
		this(table, DEFAULT_DAYS, DEFAULT_HOURS);
	}

	public TimeTableModel(KTable table, String[] days, String[] hours) {
		super(table);
		this.days = days;
		this.hours = hours;
	}

	@Override public int getFixedHeaderColumnCount() 							{ return 1; }
	@Override public int getFixedHeaderRowCount() 								{ return 1; }
	@Override public int getFixedSelectableColumnCount() 						{ return 0; }
	@Override public int getFixedSelectableRowCount() 							{ return 0; }

	@Override public int doGetColumnCount() 									{ return 1 + days.length; }
	@Override public int doGetRowCount() 										{ return 1 + hours.length; }

	@Override public int getInitialColumnWidth(int col) 						{ return 60; }
	@Override public int getInitialRowHeight(int row) 							{ return row == 0 ? 24: 36; }
	@Override public int getRowHeightMinimum() 									{ return 36; }
	
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
				default: return "";
			}
		}
	}
}
