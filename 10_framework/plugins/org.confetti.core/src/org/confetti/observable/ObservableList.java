package org.confetti.observable;

import java.util.LinkedList;
import java.util.List;

public class ObservableList<T> {

	private final List<T> list = new LinkedList<>();
	private List<ObservableListener<T>> listeners = new LinkedList<>();
	
	ObservableList() {
	}

	public Iterable<T> getList() { return list; }
	
	void addItem(T item) {
		if (!list.contains(item)) {
			list.add(item);
			notifyListeners(null, item);
		}
	}
	
	void removeItem(T item) {
		if (list.contains(item)) {
			while (list.contains(item)) {
				list.remove(item);
			}
			notifyListeners(item, null);
		}
	}
	
	public void attachListener(ObservableListener<T> listener) {
		listeners.add(listener);
	}
	
	public void detachListener(ObservableListener<T> listener) {
		listeners.remove(listener);
	}
	
	private void notifyListeners(T oldValue, T newValue) {
		for (ObservableListener<T> listener : listeners) {
			listener.valueChanged(oldValue, newValue);
		}
	}

}
