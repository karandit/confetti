package org.confetti.rcp.views;


import org.confetti.core.DataProvider;

public class TeachersView extends AbstractEntityTableView {

	public static final String ID = "org.confetti.rcp.teachersView";

	@Override
	protected Object getInput(DataProvider dp) {
		return dp.getTeachers();
	}
	

}
