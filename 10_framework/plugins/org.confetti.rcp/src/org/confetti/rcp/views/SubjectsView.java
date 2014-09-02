package org.confetti.rcp.views;

import org.confetti.core.DataProvider;
import org.confetti.core.Subject;
import org.confetti.observable.ObservableList;

public class SubjectsView extends AbstractEntityTableView<Subject> {

	public static final String ID = "org.confetti.rcp.subjectsView";

	@Override protected ObservableList<Subject> getObservableList(DataProvider dp) { return dp.getSubjects(); }

}
