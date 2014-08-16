package org.confetti.observable;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ObservableListTest {
	
//	private boolean expectedBoolean;
	private int counter;
	
	@Test
	public void should_NotifyListeners() {
		ListMutator<String> listMut = new ListMutator<>();
		counter = 0;
		listMut.getObservableList().attachListener(new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter++;
			}
		});

		listMut.addItem("A");
		assertEquals(1, counter);
		listMut.removeItem("A");
		assertEquals(2, counter);
	}
	
}
