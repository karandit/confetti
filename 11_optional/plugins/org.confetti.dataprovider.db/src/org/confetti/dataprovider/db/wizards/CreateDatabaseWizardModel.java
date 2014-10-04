package org.confetti.dataprovider.db.wizards;

import java.util.List;

/**
 * @author Gabor Bubla
 */
public class CreateDatabaseWizardModel {

	private String instituteName;
	private String comment;
	private List<String> days;
	private List<String> hours;

	public CreateDatabaseWizardModel(String instituteName, String comment, List<String> days, List<String> hours) {
		this.instituteName = instituteName;
		this.comment = comment;
		this.days = days;
		this.hours = hours;
	}

	public void setConnection(String string) {
		//TODO
	}

}
