package org.confetti.rcp.views;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.confetti.core.DataProvider;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ValueMutator;
import org.eclipse.swt.graphics.Point;
import org.junit.Before;
import org.junit.Test;

import de.kupzog.ktable.renderers.FixedCellRenderer;

public class TimeTableColumnModelTest  {
	
	private TimeTableColumnModel sut;
	
	/**
	 * +----------+--------------------+--------------------+
	 * |          |         AA         |         AB         |
	 * |          +---------+----------+---------+----------+
	 * |          |  AA1    |   AA2    |    AB1  |   AB2    |
	 * +-----+----+---------+----------+---------+----------+
	 * |  M  | 08 |                                         | 
	 * |  O  +----+-----------------------------------------+ 
	 * |  N  | 09 |                                         |
	 * |  D  +----+-----------------------------------------+
	 * |  A  | 10 |                                         |
	 * |  Y  +----+-----------------------------------------+
	 * |     | 11 |                                         |
	 * |     +----+-----------------------------------------+
	 * |     | 12 |                                         |
	 * |     +----+-----------------------------------------+
	 * |     | 13 |                                         |
	 * +-----+----+-----------------------------------------+
	 * |  T  | 08 |                                         | 
	 * |  U  +----+-----------------------------------------+ 
	 * |  E  | 09 |                                         |
	 * |  S  +----+-----------------------------------------+
	 * |  D  | 10 |                                         |
	 * |  A  +----+-----------------------------------------+
	 * |  Y  | 11 |                                         |
	 * |     +----+-----------------------------------------+
	 * |     | 12 |                                         |
	 * |     +----+-----------------------------------------+
	 * |     | 13 |                                         |
	 * +-----+----+-----------------------------------------+
	 * 
	 * 
	 */
	@Before
	public void setUp() {
		DataProvider dp = mock(DataProvider.class);
		when(dp.getDays()).thenReturn(null);
		StudentGroup sg = mockStudentGroup("A", 
							mockStudentGroup("AA",
								mockStudentGroup("AA1"),
								mockStudentGroup("AA2")
							),
							mockStudentGroup("AB",
								mockStudentGroup("AB1"),
								mockStudentGroup("AB2")
							)
		);
		sut = new TimeTableColumnModel(null, dp, sg);
		sut.initialize();
	}
	
	@Test public void testGetFixedHeaderColumnCount() 		{ assertEquals(2, sut.getFixedHeaderColumnCount()); }
	@Test public void testGetFixedHeaderRowCount() 			{ assertEquals(2, sut.getFixedHeaderRowCount()); }
	@Test public void testGetFixedSelectableColumnCount() 	{ assertEquals(0, sut.getFixedSelectableColumnCount()); }
	@Test public void testGetFixedSelectableRowCount() 		{ assertEquals(0, sut.getFixedSelectableRowCount()); }

	@Test public void testDoGetRowCount() 					{ assertEquals(2, sut.doGetRowCount()); }
	@Test public void testDoGetColumnCount() 				{ assertEquals(6, sut.doGetColumnCount()); }
	
	@Test public void testGetInitialColumnWidth() 			{ assertEquals(60, sut.getInitialColumnWidth(0)); } 
	@Test public void testGetInitialRowHeight() 			{ assertEquals(48, sut.getInitialRowHeight(0)); } 
	@Test public void testGetRowHeightMinimum() 			{ assertEquals(24, sut.getRowHeightMinimum()); } 

	@Test public void testIsColumnResizable() 				{ assertFalse(sut.isColumnResizable(0)); } 
	@Test public void testIsRowResizable() 					{ assertFalse(sut.isRowResizable(0)); } 
	
	@Test public void testDoGetCellEditor() 				{ assertNull(sut.getCellEditor(0, 0)); } 

	@Test
	public void testBelongToCell_TopLeft_Blank_Corner() {
		assertPoint(0, 0, sut.belongsToCell(0, 0));
		assertPoint(0, 0, sut.belongsToCell(1, 0));
		assertPoint(0, 0, sut.belongsToCell(0, 1));
		assertPoint(0, 0, sut.belongsToCell(1, 1));
	}

	@Test
	public void testBelongToCell_StudentGroup_Names() {
		assertPoint(2, 0, sut.belongsToCell(2, 0));
//		assertPoint(2, 0, sut.belongsToCell(3, 0));
		assertPoint(4, 0, sut.belongsToCell(4, 0));
//		assertPoint(4, 0, sut.belongsToCell(5, 0));
		assertPoint(2, 1, sut.belongsToCell(2, 1));
		assertPoint(3, 1, sut.belongsToCell(3, 1));
		assertPoint(4, 1, sut.belongsToCell(4, 1));
		assertPoint(5, 1, sut.belongsToCell(5, 1));
	}
	
	@Test
	public void testDoGetCellRenderer() {
		assertTrue(sut.getCellRenderer(0, 0) instanceof FixedCellRenderer);
	}

//	@Test public void testDoGetContentAt() 					{ assertEquals(CONTENT, sut.getContentAt(0, 0)); }
	//----------------------- helpers ----------------------------------------------------------------------------------
	private StudentGroup mockStudentGroup(String name, StudentGroup...children) {
		StudentGroup sg = mock(StudentGroup.class);
		when(sg.getName()).thenReturn(new ValueMutator<>(this, name).getObservableValue());
		when(sg.getChildren()).thenReturn(new ListMutator<>(asList(children)).getObservableList());
		return sg;
	}
	
	private void assertPoint(int x, int y, Point actualPoint) {
		assertEquals(new Point(x, y), actualPoint);
	}

}