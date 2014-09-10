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

	private final static String[] DAYS = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"}; 
	public static final KTableCellRenderer RENDERER = new DefaultCellRenderer(STYLE_PUSH);
	protected static final FixedCellRenderer FIXED_RENDERER = new FixedCellRenderer(STYLE_PUSH);
	
	public TimeTableModel(KTable table) {
		super(table);
	}

	@Override public int getFixedHeaderColumnCount() 							{ return 1; }
	@Override public int getFixedHeaderRowCount() 								{ return 1; }
	@Override public int getFixedSelectableColumnCount() 						{ return 0; }
	@Override public int getFixedSelectableRowCount() 							{ return 0; }

	@Override public int doGetColumnCount() 									{ return 6; }
	@Override public int doGetRowCount() 										{ return 10; }

	@Override public int getInitialColumnWidth(int arg0) 						{ return 60; }
	@Override public int getInitialRowHeight(int arg0) 							{ return 36; }
	@Override public int getRowHeightMinimum() 									{ return 36; }

	@Override public boolean isColumnResizable(int arg0) 						{ return false; }
	@Override public boolean isRowResizable(int arg0) 							{ return false; }

	@Override public KTableCellEditor doGetCellEditor(int arg0, int arg1) 		{ return null; }
	@Override public KTableCellRenderer doGetCellRenderer(int col, int row) 	{ return (row == 0 || col == 0) ? FIXED_RENDERER : RENDERER; }
	
	@Override public void doSetContentAt(int arg0, int arg1, Object arg2) 		{ } 
	@Override public Object doGetContentAt(int col, int row) { 
		switch (row) {
			case 0:	switch (col) {
				case 0: 	return "";
				default: 	return DAYS[(col -1) % 5];
			}
			default: switch (col) {
				case 0: return (7 + row) +": 00";
				default: return row + ":" + col;
			}
		}
		 
	}

}
