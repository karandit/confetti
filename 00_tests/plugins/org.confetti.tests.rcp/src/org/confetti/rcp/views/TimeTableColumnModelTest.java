package org.confetti.rcp.views;

import static java.util.Arrays.asList;
import static org.confetti.rcp.views.TimeTableModelTest.mockListName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.function.Consumer;

import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.SolutionSlot;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ValueMutator;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.junit.Before;
import org.junit.Test;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.renderers.FixedCellRenderer;

public class TimeTableColumnModelTest  {
	
	private final static String[] DAY_NAMES = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"}; 
	private final static String[] HOUR_NAMES = new String[] {"08", "09", "10", "11"}; 

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
	 * +-----+----+-----------------------------------------+
	 * |  T  | 08 |                                         | 
	 * |  U  +----+-----------------------------------------+ 
	 * |  E  | 09 |                                         |
	 * |  S  +----+-----------------------------------------+
	 * |  D  | 10 |                                         |
	 * |  A  +----+-----------------------------------------+
	 * |  Y  | 11 |                                         |
	 * +-----+----+-----------------------------------------+
	 * 
	 * 
	 */
	@Before
	public void setUp() {
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

		DataProvider dp = mock(DataProvider.class);
		ListMutator<Day> days = mockListName(Day.class, DAY_NAMES);
		when(dp.getDays()).thenReturn(days.getObservableList());
		ListMutator<Hour> hours = mockListName(Hour.class, HOUR_NAMES);
		when(dp.getHours()).thenReturn(hours.getObservableList());
		when(dp.getSolution()).thenReturn(new ValueMutator<Iterable<SolutionSlot>>(null, null).getObservableValue());
		
		sut = new TimeTableColumnModel(mockKTable(), dp, sg);
		sut.initialize();
	}

	@Test public void testGetFixedHeaderColumnCount() 		{ assertEquals(2, sut.getFixedHeaderColumnCount()); }
	@Test public void testGetFixedHeaderRowCount() 			{ assertEquals(2, sut.getFixedHeaderRowCount()); }
	@Test public void testGetFixedSelectableColumnCount() 	{ assertEquals(0, sut.getFixedSelectableColumnCount()); }
	@Test public void testGetFixedSelectableRowCount() 		{ assertEquals(0, sut.getFixedSelectableRowCount()); }

	@Test public void testDoGetRowCount() 					{ assertEquals(2 + 20, sut.doGetRowCount()); }
	@Test public void testDoGetColumnCount() 				{ assertEquals(2 + 4, sut.doGetColumnCount()); }
	
	@Test public void testGetInitialColumnWidth_Days() 		{ assertEquals(10, sut.getInitialColumnWidth(0)); } 
	@Test public void testGetInitialColumnWidth_Hours() 	{ assertEquals(10, sut.getInitialColumnWidth(1)); } 
	@Test public void testGetInitialColumnWidth() 			{ assertEquals(60, sut.getInitialColumnWidth(2)); } 
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
		assertPoint(2, 0, sut.belongsToCell(3, 0));
		assertPoint(4, 0, sut.belongsToCell(4, 0));
		assertPoint(4, 0, sut.belongsToCell(5, 0));
		assertPoint(2, 1, sut.belongsToCell(2, 1));
		assertPoint(3, 1, sut.belongsToCell(3, 1));
		assertPoint(4, 1, sut.belongsToCell(4, 1));
		assertPoint(5, 1, sut.belongsToCell(5, 1));
	}

	@Test
	public void testBelongToCell_Days_and_Hours() {
		for (int dayIdx = 0; dayIdx < DAY_NAMES.length; dayIdx++) {
			for (int hourIdx = 0; hourIdx < HOUR_NAMES.length; hourIdx++) {
				int row = 2 + dayIdx * HOUR_NAMES.length + hourIdx;
				assertPoint(0, 2 + dayIdx * HOUR_NAMES.length, sut.belongsToCell(0, row)); //days
				assertPoint(1, row, sut.belongsToCell(1, row)); //hours (no merging)
			}
		}
	}

	@Test
	public void testGetCellRenderer() {
		Consumer<Point> isFixedRenderer = p -> assertTrue(sut.getCellRenderer(p.x, p.y) instanceof FixedCellRenderer);
		Consumer<Point> isNotFixedRenderer = p -> assertTrue(!(sut.getCellRenderer(p.x, p.y) instanceof FixedCellRenderer));
		
		//TopLeft_Blank_Corner
		assertCells(0, sut.getFixedColumnCount(), 0, sut.getFixedRowCount(), isFixedRenderer);
		//Days and hours
		assertCells(0, sut.getFixedColumnCount(), sut.getFixedRowCount(), sut.getRowCount(), isFixedRenderer);
		//Student Group names
		assertCells(sut.getFixedColumnCount(), sut.getColumnCount(), 0, sut.getFixedRowCount(), isFixedRenderer);
		//Time table content
		assertCells(sut.getFixedColumnCount(), sut.getColumnCount(), sut.getFixedRowCount(), sut.getRowCount(), isNotFixedRenderer);
	}

	@Test
	public void testGetContentAt_TopLeft_Blank_Corner() {
		assertEquals("", sut.getContentAt(0, 0));
		assertEquals("", sut.getContentAt(1, 0));
		assertEquals("", sut.getContentAt(0, 1));
		assertEquals("", sut.getContentAt(1, 1));
	}

	@Test
	public void testGetContentAt_StudentGroup_Names() {
		assertEquals("AA", sut.getContentAt(2, 0));
		assertEquals("AA", sut.getContentAt(3, 0));
		assertEquals("AB", sut.getContentAt(4, 0));
		assertEquals("AB", sut.getContentAt(5, 0));
		assertEquals("AA1", sut.getContentAt(2, 1));
		assertEquals("AA2", sut.getContentAt(3, 1));
		assertEquals("AB1", sut.getContentAt(4, 1));
		assertEquals("AB2", sut.getContentAt(5, 1));
	}

	@Test
	public void testGetContentAt_Days_and_Hours() {
		int row = 2;
		for (String dayName : DAY_NAMES) {
			for (String hourName : HOUR_NAMES) {
				assertEquals(dayName, sut.getContentAt(0, row));
				assertEquals(hourName, sut.getContentAt(1, row));
				row++;
			}
		}
	}
	
	@Test
	public void testGetTooltipAt_StudentGroup_Names() {
		assertEquals("AA", sut.getTooltipAt(2, 0));
		assertEquals("AA", sut.getTooltipAt(3, 0));
		assertEquals("AB", sut.getTooltipAt(4, 0));
		assertEquals("AB", sut.getTooltipAt(5, 0));
		assertEquals("AA1", sut.getTooltipAt(2, 1));
		assertEquals("AA2", sut.getTooltipAt(3, 1));
		assertEquals("AB1", sut.getTooltipAt(4, 1));
		assertEquals("AB2", sut.getTooltipAt(5, 1));
	}

	@Test
	public void testGetTooltipAt_Days_and_Hours() {
		int row = 2;
		for (String dayName : DAY_NAMES) {
			for (String hourName : HOUR_NAMES) {
				assertEquals(dayName, sut.getTooltipAt(0, row));
				assertEquals(hourName, sut.getTooltipAt(1, row));
				row++;
			}
		}
	}
	
	//----------------------- helpers ----------------------------------------------------------------------------------
	public static StudentGroup mockStudentGroup(String name, StudentGroup...children) {
		StudentGroup sg = mock(StudentGroup.class);
		when(sg.getName()).thenReturn(new ValueMutator<>(null, name).getObservableValue());
		when(sg.getChildren()).thenReturn(new ListMutator<>(asList(children)).getObservableList());
		return sg;
	}

	public static KTable mockKTable() {
		KTable ktable = mock(KTable.class);
		Display display = mock(Display.class);
		when(ktable.getDisplay()).thenReturn(display);
		return ktable;
	}
	
	public static void assertPoint(int x, int y, Point actualPoint) {
		assertEquals(new Point(x, y), actualPoint);
	}

	private void assertCells(int colMin, int colMax, int rowMin, int rowMax, Consumer<Point> f) {
		for (int col = colMin; col < colMax; col++) {
			for (int row = rowMin; row < rowMax; row++) {
				f.accept(new Point(col, row));
			}
		}
	}


}