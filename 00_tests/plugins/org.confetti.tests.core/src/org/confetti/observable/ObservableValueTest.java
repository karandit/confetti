package org.confetti.observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ObservableValueTest {
	
	private static class IntWrapper {
		public int value = 0;
	}
	
	private static class BooleanWrapper {
		public boolean value = false;
	}

	@Test
	public void should_NotifyListeners() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		final BooleanWrapper expectedBoolean = new BooleanWrapper();
		nameMut.getObservableValue().attachListener(new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				assertEquals("first value", newValue);
				expectedBoolean.value = true;
			}
		});
		nameMut.setValue("first value");
		assertTrue(expectedBoolean.value);
	}

	@Test
	public void should_Not_NotifyListeners_AfterDetach() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		final IntWrapper counter = new IntWrapper();
		ObservableListener<String> listener = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter.value++;
			}
		};
		
		nameMut.getObservableValue().attachListener(listener);
		nameMut.setValue("first value");
		assertEquals(1, counter.value);
		nameMut.setValue("second value");
		assertEquals(2, counter.value);
		
		nameMut.getObservableValue().detachListener(listener);
		
		nameMut.setValue("third value");
		assertEquals(2, counter.value);
	}

	@Test
	public void should_Notify_More_Listeners() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		final IntWrapper counter = new IntWrapper();
		ObservableListener<String> listener1 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter.value++;
			}
		};
		ObservableListener<String> listener2 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter.value++;
			}
		};
		
		nameMut.getObservableValue().attachListener(listener1);
		nameMut.getObservableValue().attachListener(listener2);
		nameMut.setValue("first value");
		assertEquals(2, counter.value);
	}

	@Test
	public void should_NotNotify_Any_Listener_AfterDetachingAll() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		final IntWrapper counter = new IntWrapper();
		ObservableListener<String> listener1 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter.value++;
			}
		};
		ObservableListener<String> listener2 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter.value++;
			}
		};
		
		nameMut.getObservableValue().attachListener(listener1);
		nameMut.getObservableValue().attachListener(listener2);
		nameMut.setValue("first value");
		nameMut.getObservableValue().detachListener(listener1);
		nameMut.getObservableValue().detachListener(listener2);
		nameMut.setValue("second value");
		assertEquals(2, counter.value);
	}

	@Test
	public void should_NotNotify_DetachedListener_AfterDetaching() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		final IntWrapper counter = new IntWrapper();
		ObservableListener<String> listener1 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter.value++;
			}
		};
		ObservableListener<String> listener2 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter.value++;
			}
		};
		
		nameMut.getObservableValue().attachListener(listener1);
		nameMut.getObservableValue().attachListener(listener2);
		nameMut.setValue("first value");
		nameMut.getObservableValue().detachListener(listener1);
		nameMut.setValue("second value");
		assertEquals(3, counter.value);
	}

	@Test
	public void should_NotNotify_WhenThereIs_NoChange() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		final IntWrapper counter = new IntWrapper();
		ObservableListener<String> listener1 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter.value++;
			}
		};
		ObservableListener<String> listener2 = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter.value++;
			}
		};
		
		nameMut.getObservableValue().attachListener(listener1);
		nameMut.getObservableValue().attachListener(listener2);
		assertEquals(0, counter.value);
	}

}
