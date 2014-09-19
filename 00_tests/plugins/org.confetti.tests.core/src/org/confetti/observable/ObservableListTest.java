package org.confetti.observable;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ObservableListTest {
	
	private static class IntWrapper {
		public int value = 0;
	}
	
	@Test
	public void should_NotifyListeners() {
		ListMutator<String> listMut = new ListMutator<>();
		final IntWrapper counterAdd = new IntWrapper();
		final IntWrapper counterRemove = new IntWrapper();
		listMut.getObservableList().attachListener(new ObservableListener<String>() {
			
			@Override
			public void valueChanged(Object src, String oldValue, String newValue) {
				if (oldValue == null) {
					counterAdd.value++;
				} else if (newValue == null) {
					counterRemove.value++;
				}  
			}
		});

		assertEquals(0, counterAdd.value);
		assertEquals(0, counterRemove.value);
		listMut.addItem("A");
		assertEquals(1, counterAdd.value);
		assertEquals(0, counterRemove.value);
		listMut.removeItem("A");
		assertEquals(1, counterAdd.value);
		assertEquals(1, counterRemove.value);
	}
	
	@Test
	public void should_Not_NotifyListeners_AfterDetach() {
		ListMutator<String> listMut = new ListMutator<>();
		final IntWrapper counterAdd = new IntWrapper();
		final IntWrapper counterRemove = new IntWrapper();
		ObservableListener<String> listener = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(Object src, String oldValue, String newValue) {
				if (oldValue == null) {
					counterAdd.value++;
				} else if (newValue == null) {
					counterRemove.value++;
				}  
			}
		};

		listMut.getObservableList().attachListener(listener);
		listMut.addItem("A");
		listMut.removeItem("A");
		listMut.getObservableList().detachListener(listener);
		listMut.addItem("A");
		listMut.removeItem("A");
		assertEquals(1, counterAdd.value);
		assertEquals(1, counterRemove.value);
	}
	
	@Test
	public void should_Notify_More_Listeners() {
		ListMutator<String> listMut = new ListMutator<>();
		final IntWrapper counterAdd = new IntWrapper();
		final IntWrapper counterRemove = new IntWrapper();
		ObservableListener<String> listener1 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(Object src, String oldValue, String newValue) {
				if (oldValue == null) {
					counterAdd.value++;
				} else if (newValue == null) {
					counterRemove.value++;
				}  
			}
		};
		ObservableListener<String> listener2 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(Object src, String oldValue, String newValue) {
				if (oldValue == null) {
					counterAdd.value++;
				} else if (newValue == null) {
					counterRemove.value++;
				}  
			}
		};
		
		listMut.getObservableList().attachListener(listener1);
		listMut.getObservableList().attachListener(listener2);
		listMut.addItem("A");
		listMut.removeItem("A");
		assertEquals(2, counterAdd.value);
		assertEquals(2, counterRemove.value);
	}
	
}
