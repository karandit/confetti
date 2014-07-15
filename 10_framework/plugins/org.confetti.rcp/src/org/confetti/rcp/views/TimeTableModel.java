package org.confetti.rcp.views;

import org.eclipse.swt.SWT;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableNoScrollModel;
import de.kupzog.ktable.renderers.TextCellRenderer;

/**
 * @author Gabor Bubla
 */
public class TimeTableModel extends KTableNoScrollModel {

	public TimeTableModel(KTable table) {
		super(table);
	}

	@Override public int getFixedHeaderColumnCount() 			{ return 1; }
	@Override public int getFixedHeaderRowCount() 				{ return 1; }
	@Override public int getFixedSelectableColumnCount() 		{ return 1; }
	@Override public int getFixedSelectableRowCount() 			{ return 1; }

	@Override public int doGetColumnCount() 					{ return 6; }
	@Override public int doGetRowCount() 						{ return 6; }

	@Override public int getInitialColumnWidth(int arg0) 		{ return 20; }
	@Override public int getInitialRowHeight(int arg0) 			{ return 20; }
	@Override public int getRowHeightMinimum() 					{ return 20; }

	@Override public boolean isColumnResizable(int arg0) 		{ return false; }
	@Override public boolean isRowResizable(int arg0) 			{ return false; }

	@Override public KTableCellEditor doGetCellEditor(int arg0, int arg1) 		{ return null; }
	@Override public KTableCellRenderer doGetCellRenderer(int arg0, int arg1) 	{ return new TextCellRenderer(SWT.NONE); }
	
	@Override public Object doGetContentAt(int arg0, int arg1) 	{ return "a"; }
	@Override public void doSetContentAt(int arg0, int arg1, Object arg2) { } 

}
