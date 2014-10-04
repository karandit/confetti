package org.confetti.observable;

public class ListMutator<T> {
	
	private final ObservableList<T> observList = new ObservableList<>();
	
	public ListMutator() {
	}
	
	public ListMutator(Iterable<T> initialItems) {
	    for (T item : initialItems) {
            observList.addItem(item);
        }
	}
	
	public ObservableList<T> getObservableList() {
		return observList;
	}

	public void addItem(T item) {
		observList.addItem(item);
	}

	public void removeItem(T item) {
		observList.removeItem(item);
	}
	
}
