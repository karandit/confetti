package org.confetti.observable;

public class ListMutator<T> {
	
	private ObservableList<T> observList = new ObservableList<>();
	
	public ListMutator() {
	}
	
	public ListMutator(ObservableList<T> observList) {
		this.observList = observList;
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
