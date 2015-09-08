package org.confetti.rcp.views;

import static org.confetti.rcp.views.TimeTableColumnModelTest.assertPoint;
import static org.confetti.rcp.views.TimeTableColumnModelTest.mockStudentGroup;
import static org.confetti.rcp.views.TimeTableModelTest.mockListName;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.SolutionSlot;
import org.confetti.core.StudentGroup;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ValueMutator;
import org.junit.Before;
import org.junit.Test;

public class TimeTable_Asymmetric_ColumnModelTest {
		
	private TimeTableColumnModel sut;
	
	/**
	 * +----------+------------------------+--------------------+-----------+
	 * |          |         AA             |         AB         |     AC    |
	 * |          +-----------+------------+---------+----------+           |
	 * |          |  AA1      |   AA2      |    AB1  |   AB2    |           |
	 * |          |           |-----+------|         |----------+           |
	 * |          |           | AA21| AA22 |         |   AB21   |           |
	 * +-----+----+-----------+-----+------+---------+----------+-----------+
	 * |  M  | 08 |                                                         |
	 * |  O  +----+---------------------------------------------------------+
	 */
	@Before
	public void setUp() {
		StudentGroup sg = mockStudentGroup("A", 
							mockStudentGroup("AA",
								mockStudentGroup("AA1"),
								mockStudentGroup("AA2",
									mockStudentGroup("AA21"),
									mockStudentGroup("AA22")
								)
							),
							mockStudentGroup("AB",
								mockStudentGroup("AB1"),
								mockStudentGroup("AB2",
									mockStudentGroup("AB21"))
							),
							mockStudentGroup("AC")
						);

		DataProvider dp = mock(DataProvider.class);
		ListMutator<Day> days = mockListName(Day.class, "Monday", "Tuesday");
		when(dp.getDays()).thenReturn(days.getObservableList());
		ListMutator<Hour> hours = mockListName(Hour.class, "08", "09", "10", "11");
		when(dp.getHours()).thenReturn(hours.getObservableList());
		when(dp.getSolution()).thenReturn(new ValueMutator<Iterable<SolutionSlot>>(null, null).getObservableValue());

		sut = new TimeTableColumnModel(null, dp, sg);
		sut.initialize();
	}
	
	@Test public void testGetFixedHeaderColumnCount() 		{ assertEquals(2, sut.getFixedHeaderColumnCount()); }
	@Test public void testGetFixedHeaderRowCount() 			{ assertEquals(3, sut.getFixedHeaderRowCount()); }
	@Test public void testGetFixedSelectableColumnCount() 	{ assertEquals(0, sut.getFixedSelectableColumnCount()); }
	@Test public void testGetFixedSelectableRowCount() 		{ assertEquals(0, sut.getFixedSelectableRowCount()); }

	@Test public void testDoGetColumnCount() 				{ assertEquals(2 + 6, sut.doGetColumnCount()); }
	@Test public void testDoGetRowCount() 					{ assertEquals(3 + 8, sut.doGetRowCount()); }

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
		assertPoint(2, 0, sut.belongsToCell(4, 0));
		assertPoint(5, 0, sut.belongsToCell(5, 0));
		assertPoint(5, 0, sut.belongsToCell(6, 0));
		assertPoint(7, 0, sut.belongsToCell(7, 0));
	
		assertPoint(2, 1, sut.belongsToCell(2, 1));
		assertPoint(3, 1, sut.belongsToCell(3, 1));
		assertPoint(3, 1, sut.belongsToCell(4, 1));
		assertPoint(5, 1, sut.belongsToCell(5, 1));
		assertPoint(6, 1, sut.belongsToCell(6, 1));
		assertPoint(7, 1, sut.belongsToCell(7, 1));

		assertPoint(2, 2, sut.belongsToCell(2, 2));
		assertPoint(3, 2, sut.belongsToCell(3, 2));
		assertPoint(4, 2, sut.belongsToCell(4, 2));
		assertPoint(5, 2, sut.belongsToCell(5, 2));
		assertPoint(6, 2, sut.belongsToCell(6, 2));
		assertPoint(7, 2, sut.belongsToCell(7, 2));
	}

}
