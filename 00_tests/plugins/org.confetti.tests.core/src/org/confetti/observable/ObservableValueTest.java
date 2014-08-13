package org.confetti.observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ObservableValueTest {
	
	@Test
	public void should_NotifyListeners() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		final boolean[] expected = new boolean[] {false};
		nameMut.getObservableValue().attachListener(new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				assertEquals("first value", newValue);
				expected[0] = true;
			}
		});
		nameMut.setValue("first value");
		assertTrue(expected[0]);
		
	}

	@Test
	public void should_Not_NotifyListeners_AfterDetach() {
		ValueMutator<String> nameMut = new ValueMutator<>();
		final int[] counter = new int[] {0};
		ObservableListener<String> listener = new ObservableListener<String>() {
			
			@Override
			public void valueChanged(String oldValue, String newValue) {
				counter[0]++;
			}
		};
		
		nameMut.getObservableValue().attachListener(listener);
		nameMut.setValue("first value");
		assertEquals(1, counter[0]);
		nameMut.setValue("second value");
		assertEquals(2, counter[0]);
		
		nameMut.getObservableValue().detachListener(listener);
		
		nameMut.setValue("third value");
		assertEquals(2, counter[0]);
	}

	@Test
	public void should_Notify_More_Listeners() {
		//TODO:
	}

	@Test
	public void should_NotNotify_Any_Listener_AfterDetachingAll() {
		//TODO:
	}

	@Test
	public void should_NotNotify_DetachedListener_AfterDetaching() {
		//TODO:
	}

	@Test
	public void should_NotNotify_WhenThereIs_NoChange() {
		//TODO:
	}


}
