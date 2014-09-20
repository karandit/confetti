package org.confetti.rcp.views;

import static de.kupzog.ktable.renderers.DefaultCellRenderer.STYLE_FLAT;
import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableNoScrollModel;
import de.kupzog.ktable.renderers.FixedCellRenderer;

/**
 * @author Gabor Bubla
 */
public class TimeTableNotAvailableModel extends KTableNoScrollModel {

	protected static final FixedCellRenderer FIXED_RENDERER = new FixedCellRenderer(STYLE_FLAT);
	
	public TimeTableNotAvailableModel(KTable table) {
		super(table);
	}
	
	@Override public int getFixedHeaderColumnCount() 							{ return 1; }
	@Override public int getFixedHeaderRowCount() 								{ return 1; }
	@Override public int getFixedSelectableColumnCount() 						{ return 0; }
	@Override public int getFixedSelectableRowCount() 							{ return 0; }

	@Override public int doGetColumnCount() 									{ return 1; }
	@Override public int doGetRowCount() 										{ return 1; }

	@Override public int getInitialColumnWidth(int col) 						{ return 60; }
	@Override public int getInitialRowHeight(int row) 							{ return 24; }
	@Override public int getRowHeightMinimum() 									{ return 48; }
	
	@Override public boolean isColumnResizable(int col) 						{ return false; }
	@Override public boolean isRowResizable(int row) 							{ return false; }

	@Override public KTableCellEditor doGetCellEditor(int arg0, int arg1) 		{ return null; }
	@Override public KTableCellRenderer doGetCellRenderer(int col, int row) 	{ return FIXED_RENDERER; }
	
	@Override public void doSetContentAt(int arg0, int arg1, Object arg2) 		{ } 
	@Override
	public Object doGetContentAt(int col, int row) { 
		return "Timetable not available";
	}
	
}
