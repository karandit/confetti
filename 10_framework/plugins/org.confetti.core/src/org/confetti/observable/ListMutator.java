package org.confetti.observable;

public class ListMutator<T> {
	private final ObservableList<T> observList = new ObservableList<>();
	
	public ListMutator() {
	}

	public ObservableList<T> getObservableList() {
		return observList;
	}

	public void addItem(T itemToAdd) {
		//TODO: implement it
	}

	public void removeItem(T itemToRemove) {
		//TODO: implement it
	}
}
