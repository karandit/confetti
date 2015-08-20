package org.confetti.rcp.views;

import static de.kupzog.ktable.renderers.DefaultCellRenderer.STYLE_PUSH;

import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;

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
	private final StudentGroup sg;

	//----------------------------- constructors -----------------------------------------------------------------------
	public TimeTableColumnModel(KTable table, DataProvider dp, StudentGroup sg) {
		super(table);
//		this.dp = dp;
		this.sg = sg;
	}
	
	//----------------------------- KTableNoScrollModel's API ----------------------------------------------------------
	@Override public int getFixedHeaderColumnCount() 							{ return 2; }
	@Override public int getFixedHeaderRowCount() 								{ return 1; }
	@Override public int getFixedSelectableColumnCount() 						{ return 0; }
	@Override public int getFixedSelectableRowCount() 							{ return 0; }

	@Override public int doGetColumnCount() 									{ return 2 + dagWidth(sg);}

	private static int dagWidth(StudentGroup sg) {
		int childWidth = 0;
		for (StudentGroup child : sg.getChildren().getList()) {
			childWidth += dagWidth(child);
		}
		return Math.max(1, childWidth);
	}

	private static int dagDepth(StudentGroup sg) {
		int childDepth = 0;
		for (StudentGroup child : sg.getChildren().getList()) {
			childDepth = Math.max(childDepth, dagDepth(child));
		}
		return 1 +  childDepth;
	}
	
	

	@Override
	public int doGetRowCount() {
		return dagDepth(sg); // + size(dp.getDays().getList()) * size(dp.getHours().getList());
	}

	@Override public int getInitialColumnWidth(int col) 						{ return 60; }
	@Override public int getInitialRowHeight(int row) 							{ return 48; }
	@Override public int getRowHeightMinimum() 									{ return 24; }
	
	@Override public boolean isColumnResizable(int col) 						{ return false; }
	@Override public boolean isRowResizable(int row) 							{ return false; }

	@Override public KTableCellEditor doGetCellEditor(int arg0, int arg1) 		{ return null; }
	@Override public KTableCellRenderer doGetCellRenderer(int col, int row) 	{ return (row == 0 || col == 1 || col == 0) ? FIXED_RENDERER : RENDERER; }
	
	@Override public void doSetContentAt(int arg0, int arg1, Object arg2) 		{ }
	@Override public Object doGetContentAt(int col, int row) 					{ return "alam"; }
}
