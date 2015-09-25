package org.confetti.rcp.views;

import org.confetti.core.DataProvider;
import org.confetti.core.Teacher;
import org.confetti.observable.ObservableList;

public class TeachersView extends AbstractEntityTableView<Teacher> {

	public static final String ID = "org.confetti.rcp.teachersView"; //$NON-NLS-1$

	@Override protected ObservableList<Teacher> getObservableList(DataProvider dp) { return dp.getTeachers(); }
	

}
