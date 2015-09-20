package org.confetti.observable;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObservableList<T> {

	private final List<T> list = new LinkedList<>();
	private List<ObservableListener<T>> listeners = new LinkedList<>();

	ObservableList() {
	}

	public Iterable<T> getList() {
		return list;
	}

	public Stream<T> stream() {
		return list.stream();
	}

	public <R> List<R> toList(Function<? super T, R> mapper) {
		return list.stream().map(mapper).collect(Collectors.toList());
	}

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
		// TODO replace it in the future with useful source
		listeners.forEach(listener -> listener.valueChanged(null, oldValue,
				newValue));
	}

}
