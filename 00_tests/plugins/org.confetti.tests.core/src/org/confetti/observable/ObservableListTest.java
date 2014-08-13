package org.confetti.observable;

import org.junit.Test;

public class ObservableListTest {
	
	@Test
	public void should_NotifyListeners() {
		ListMutator<String> listMut = new ListMutator<>();
		listMut.getObservableList().attachListener(new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
			
			}
		});
		listMut.addItem("A");
		
	}
}
