package org.confetti.observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ObservableValueTest {
	
	private boolean expectedBoolean;
	private int counter;

	@Test
	public void should_NotifyListeners() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		expectedBoolean = false;
		nameMut.getObservableValue().attachListener(new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				assertEquals("first value", newValue);
				expectedBoolean = true;
			}
		});
		nameMut.setValue("first value");
		assertTrue(expectedBoolean);
	}

	@Test
	public void should_Not_NotifyListeners_AfterDetach() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		counter = 0;
		ObservableListener<String> listener = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter++;
			}
		};
		
		nameMut.getObservableValue().attachListener(listener);
		nameMut.setValue("first value");
		assertEquals(1, counter);
		nameMut.setValue("second value");
		assertEquals(2, counter);
		
		nameMut.getObservableValue().detachListener(listener);
		
		nameMut.setValue("third value");
		assertEquals(2, counter);
	}

	@Test
	public void should_Notify_More_Listeners() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		counter = 0;
		ObservableListener<String> listener1 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter++;
			}
		};
		ObservableListener<String> listener2 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter++;
			}
		};
		
		nameMut.getObservableValue().attachListener(listener1);
		nameMut.getObservableValue().attachListener(listener2);
		nameMut.setValue("first value");
		assertEquals(2, counter);
	}

	@Test
	public void should_NotNotify_Any_Listener_AfterDetachingAll() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		counter = 0;
		ObservableListener<String> listener1 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter++;
			}
		};
		ObservableListener<String> listener2 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter++;
			}
		};
		
		nameMut.getObservableValue().attachListener(listener1);
		nameMut.getObservableValue().attachListener(listener2);
		nameMut.setValue("first value");
		nameMut.getObservableValue().detachListener(listener1);
		nameMut.getObservableValue().detachListener(listener2);
		nameMut.setValue("second value");
		assertEquals(2, counter);
	}

	@Test
	public void should_NotNotify_DetachedListener_AfterDetaching() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		counter = 0;
		ObservableListener<String> listener1 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter++;
			}
		};
		ObservableListener<String> listener2 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter++;
			}
		};
		
		nameMut.getObservableValue().attachListener(listener1);
		nameMut.getObservableValue().attachListener(listener2);
		nameMut.setValue("first value");
		nameMut.getObservableValue().detachListener(listener1);
		nameMut.setValue("second value");
		assertEquals(3, counter);
	}

	@Test
	public void should_NotNotify_WhenThereIs_NoChange() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		counter = 0;
		ObservableListener<String> listener1 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter++;
			}
		};
		ObservableListener<String> listener2 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter++;
			}
		};
		
		nameMut.getObservableValue().attachListener(listener1);
		nameMut.getObservableValue().attachListener(listener2);
		assertEquals(0, counter);
	}

}
