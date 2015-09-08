package org.confetti.rcp.views;

import static de.kupzog.ktable.renderers.DefaultCellRenderer.STYLE_PUSH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ValueMutator;
import org.junit.Before;
import org.junit.Test;

import de.kupzog.ktable.renderers.DefaultCellRenderer;
import de.kupzog.ktable.renderers.FixedCellRenderer;

public class TimeTableModelTest {

	private TimeTableModel sut;
	
	@Before
	public void setup() {
		DataProvider dp = mock(DataProvider.class);
		
		ListMutator<Day> days = mockListName(Day.class, 
				"Monday", 
				"Tuesday", 
				"Wednesday", 
				"Thursday", 
				"Friday");
		when(dp.getDays()).thenReturn(days.getObservableList());

		ListMutator<Hour> hours = mockListName(Hour.class, 
				"08 :00 - 08:45", 
				"09 :00 - 09:45", 
				"10 :00 - 10:45", 
				"11 :00 - 11:45");
		when(dp.getHours()).thenReturn(hours.getObservableList());
		
		sut = new TimeTableModel(null, dp);
		sut.initialize();
	}
	
	@Test public void testGetFixedHeaderColumnCount() 		{ assertEquals(1, sut.getFixedHeaderColumnCount()); }
	@Test public void testGetFixedHeaderRowCount() 			{ assertEquals(1, sut.getFixedHeaderRowCount()); }
	@Test public void testGetFixedSelectableColumnCount() 	{ assertEquals(0, sut.getFixedSelectableColumnCount()); }
	@Test public void testGetFixedSelectableRowCount() 		{ assertEquals(0, sut.getFixedSelectableRowCount()); }

	@Test public void testDoGetRowCount() 					{ assertEquals(5, sut.doGetRowCount()); }
	@Test public void testDoGetColumnCount() 				{ assertEquals(6, sut.doGetColumnCount()); }
	
	@Test public void testGetInitialColumnWidth_First() 	{ assertEquals(10, sut.getInitialColumnWidth(0)); } 
	@Test public void testGetInitialColumnWidth() 			{ assertEquals(60, sut.getInitialColumnWidth(1)); } 
	@Test public void testGetInitialRowHeight_ForHeader()	{ assertEquals(24, sut.getInitialRowHeight(0)); } 
	@Test public void testGetInitialRowHeight_ForContent()	{ assertEquals(48, sut.getInitialRowHeight(1)); } 
	@Test public void testGetRowHeightMinimum() 			{ assertEquals(48, sut.getRowHeightMinimum()); } 

	@Test public void testIsColumnResizable() 				{ assertFalse(sut.isColumnResizable(0)); } 
	@Test public void testIsRowResizable() 					{ assertFalse(sut.isRowResizable(0)); } 
	
	@Test public void testDoGetCellEditor() 				{ assertNull(sut.getCellEditor(0, 0)); } 

	@Test
	public void testDoGetCellRenderer() {
		assertTrue(sut.getCellRenderer(0, 0) instanceof FixedCellRenderer);
		assertTrue(sut.getCellRenderer(1, 1) instanceof DefaultCellRenderer);
		assertEquals(STYLE_PUSH, ((DefaultCellRenderer) sut.getCellRenderer(1, 1)).getStyle() & STYLE_PUSH);
	}

	@Test
	public void testDoGetContentAt() {
		assertEquals("", sut.getContentAt(0, 0));

		assertEquals("Monday", sut.getContentAt(1, 0));
		assertEquals("Tuesday", sut.getContentAt(2, 0));
		assertEquals("Wednesday", sut.getContentAt(3, 0));
		assertEquals("Thursday", sut.getContentAt(4, 0));
		assertEquals("Friday", sut.getContentAt(5, 0));

		assertEquals("08 :00 - 08:45", sut.getContentAt(0, 1));
		assertEquals("09 :00 - 09:45", sut.getContentAt(0, 2));
		assertEquals("10 :00 - 10:45", sut.getContentAt(0, 3));
		assertEquals("11 :00 - 11:45", sut.getContentAt(0, 4));
		
		assertEquals("", sut.getContentAt(1, 1));
	}

	//---------------------- helpers -----------------------------------------------------------------------------------
	public static <T extends Nameable> T mockName(Class<T> clazz, String name) {
		T nameable = mock(clazz);
		when(nameable.getName()).thenReturn(new ValueMutator<>(null, name).getObservableValue());
		return nameable;
	}
	public static <T extends Nameable> ListMutator<T> mockListName(Class<T> clazz, String... names) {
		ListMutator<T> list = new ListMutator<>();
		for (String name : names) {
			list.addItem(mockName(clazz, name));
		}
		return list;
	}

}
