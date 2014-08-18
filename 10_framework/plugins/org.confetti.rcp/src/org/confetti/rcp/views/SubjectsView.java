package org.confetti.rcp.views;

import org.confetti.core.DataProvider;

public class SubjectsView extends AbstractEntityTableView {

	public static final String ID = "org.confetti.rcp.subjectsView";

	@Override
	protected Object getInput(DataProvider dp) {
		return dp.getSubjects().getList();
	}

}
