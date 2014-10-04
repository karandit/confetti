package org.confetti.dataprovider.db.wizards;

import java.util.List;

import org.confetti.dataprovider.db.ConnectionDescriptor;

/**
 * @author Gabor Bubla
 */
public class CreateDatabaseWizardModel {

	private final String instituteName;
	private final String comment;
	private final List<String> days;
    private final List<String> hours;
	private ConnectionDescriptor cDesc;

	public CreateDatabaseWizardModel(String instituteName, String comment, List<String> days, List<String> hours) {
		this.instituteName = instituteName;
		this.comment = comment;
		this.days = days;
		this.hours = hours;
	}

	public void setConnection(ConnectionDescriptor cDesc) { this.cDesc = cDesc; }
	public ConnectionDescriptor getConnection() { return cDesc; }

    public String getInstituteName() { return instituteName; }
    public String getComment() { return comment; }
    public List<String> getDays() { return days; }
    public List<String> getHours() { return hours; }
    
}
