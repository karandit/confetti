package org.confetti.rcp.views;

import static de.kupzog.ktable.renderers.DefaultCellRenderer.STYLE_PUSH;

import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.eclipse.swt.graphics.Point;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableNoScrollModel;
import de.kupzog.ktable.renderers.FixedCellRenderer;

public class TimeTableColumnModel extends KTableNoScrollModel {

	//----------------------------- constants --------------------------------------------------------------------------
//	private static final KTableCellRenderer RENDERER = new DefaultCellRenderer(STYLE_PUSH);
	private static final FixedCellRenderer FIXED_RENDERER = new FixedCellRenderer(STYLE_PUSH);
	
	//----------------------------- fields -----------------------------------------------------------------------------
//	private final DataProvider dp;
//	private final StudentGroup sg;
	private final int namesWidth;
	private final int namesHeight;
	
	//----------------------------- constructors -----------------------------------------------------------------------
	public TimeTableColumnModel(KTable table, DataProvider dp, StudentGroup sg) {
		super(table);
//		this.dp = dp;
//		this.sg = sg;
		this.namesWidth = calcWidth(sg);
		this.namesHeight = calcHeight(sg) - 1;
	}
	
	//----------------------------- KTableNoScrollModel's API ----------------------------------------------------------
	@Override public int getFixedHeaderColumnCount() 							{ return 2; }
	@Override public int getFixedHeaderRowCount() 								{ return namesHeight; }
	@Override public int getFixedSelectableColumnCount() 						{ return 0; }
	@Override public int getFixedSelectableRowCount() 							{ return 0; }

	@Override public int doGetColumnCount() 									{ return 2 + namesWidth;}
	@Override public int doGetRowCount() 										{ return namesHeight; }
	
	@Override public int getInitialColumnWidth(int col) 						{ return 60; }
	@Override public int getInitialRowHeight(int row) 							{ return 48; }
	@Override public int getRowHeightMinimum() 									{ return 24; }
	
	@Override public boolean isColumnResizable(int col) 						{ return false; }
	@Override public boolean isRowResizable(int row) 							{ return false; }

	@Override public KTableCellEditor doGetCellEditor(int arg0, int arg1) 		{ return null; }
	@Override public KTableCellRenderer doGetCellRenderer(int col, int row) 	{ return FIXED_RENDERER; }
	
	@Override public void doSetContentAt(int arg0, int arg1, Object arg2) 		{ }
	@Override public Object doGetContentAt(int col, int row) 					{ return ""; }

	@Override
	public Point belongsToCell(int col, int row) {
		//top left blank corner
		if (col < getFixedHeaderColumnCount() && row < getFixedHeaderRowCount()) {
			return new Point(0, 0);
		}
		//student group names
//		if (row < getFixedHeaderRowCount()) {
//			return new Point(0, 0);
//		}
		
		return super.belongsToCell(col, row);
	}
	
	//----------------------------- helpers ----------------------------------------------------------------------------
	private static int calcWidth(StudentGroup sg) {
		int childWidth = 0;
		for (StudentGroup child : sg.getChildren().getList()) {
			childWidth += calcWidth(child);
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
