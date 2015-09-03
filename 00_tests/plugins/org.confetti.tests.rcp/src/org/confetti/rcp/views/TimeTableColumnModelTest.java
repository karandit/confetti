package org.confetti.rcp.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ListMutator;
import org.junit.Before;
import org.junit.Test;

public class TimeTableColumnModelTest  {
	
	private TimeTableColumnModel sut;
	
	@Before
	public void setUp() {
		DataProvider dp = mock(DataProvider.class);
		when(dp.getDays()).thenReturn(null);
		StudentGroup sg = mock(StudentGroup.class);
		when(sg.getChildren()).thenReturn(new ListMutator<StudentGroup>().getObservableList());
		
		
		sut = new TimeTableColumnModel(null, dp, sg);
		sut.initialize();
	}
	
	@Test public void testGetFixedHeaderColumnCount() 		{ assertEquals(2, sut.getFixedHeaderColumnCount()); }
	@Test public void testGetFixedHeaderRowCount() 			{ assertEquals(1, sut.getFixedHeaderRowCount()); }
	@Test public void testGetFixedSelectableColumnCount() 	{ assertEquals(0, sut.getFixedSelectableColumnCount()); }
	@Test public void testGetFixedSelectableRowCount() 		{ assertEquals(0, sut.getFixedSelectableRowCount()); }

	@Test public void testDoGetRowCount() 					{ assertEquals(1, sut.doGetRowCount()); }
	@Test public void testDoGetColumnCount() 				{ assertEquals(3, sut.doGetColumnCount()); }
	
	@Test public void testGetInitialColumnWidth() 			{ assertEquals(60, sut.getInitialColumnWidth(0)); } 
	@Test public void testGetInitialRowHeight() 			{ assertEquals(48, sut.getInitialRowHeight(0)); } 
	@Test public void testGetRowHeightMinimum() 			{ assertEquals(24, sut.getRowHeightMinimum()); } 

	@Test public void testIsColumnResizable() 				{ assertFalse(sut.isColumnResizable(0)); } 
	@Test public void testIsRowResizable() 					{ assertFalse(sut.isRowResizable(0)); } 
	
	@Test public void testDoGetCellEditorIntInt() 			{ assertEquals(0, sut.getFixedSelectableRowCount()); } 
	@Test public void testDoGetCellRendererIntInt() 		{ assertEquals(0, sut.getFixedSelectableRowCount()); } 

//	@Test public void testDoGetContentAt() 					{ assertEquals(CONTENT, sut.getContentAt(0, 0)); }


}