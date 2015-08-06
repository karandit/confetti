package org.confetti.rcp.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.confetti.core.DataProvider;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TimeTableColumnModelTest  {
	
	private TimeTableColumnModel sut;
	
	@Before
	public void setUp() {
		DataProvider dp = mock(DataProvider.class);
		when(dp.getDays()).thenReturn(null);
		sut = new TimeTableColumnModel(null, dp, null);
		sut.initialize();
	}
	
	@Test public void testGetFixedHeaderColumnCount() 		{ assertEquals(1, sut.getFixedHeaderColumnCount()); }
	@Test public void testGetFixedHeaderRowCount() 			{ assertEquals(1, sut.getFixedHeaderRowCount()); }
	@Test public void testGetFixedSelectableColumnCount() 	{ assertEquals(0, sut.getFixedSelectableColumnCount()); }
	@Test public void testGetFixedSelectableRowCount() 		{ assertEquals(0, sut.getFixedSelectableRowCount()); }

	@Test public void testDoGetRowCount() 					{ assertEquals(1, sut.doGetRowCount()); }
	@Test public void testDoGetColumnCount() 				{ assertEquals(1, sut.doGetColumnCount()); }
	
	@Test public void testGetInitialColumnWidth() 			{ assertEquals(60, sut.getInitialColumnWidth(0)); } 
	@Test public void testGetInitialRowHeight() 			{ assertEquals(24, sut.getInitialRowHeight(0)); } 
	@Test public void testGetRowHeightMinimum() 			{ assertEquals(48, sut.getRowHeightMinimum()); } 

	@Test public void testIsColumnResizable() 				{ assertFalse(sut.isColumnResizable(0)); } 
	@Test public void testIsRowResizable() 					{ assertFalse(sut.isRowResizable(0)); } 
	
	@Test public void testDoGetCellEditorIntInt() 			{ assertEquals(0, sut.getFixedSelectableRowCount()); } 
	@Test public void testDoGetCellRendererIntInt() 		{ assertEquals(0, sut.getFixedSelectableRowCount()); } 

//	@Test public void testDoGetContentAt() 					{ assertEquals(CONTENT, sut.getContentAt(0, 0)); }


}