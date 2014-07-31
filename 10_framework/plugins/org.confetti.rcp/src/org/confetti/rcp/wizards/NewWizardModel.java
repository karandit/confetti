package org.confetti.rcp.wizards;

import java.util.List;

/**
 * @author Bubla Gabor
 */
public class NewWizardModel {

	private String instituteName;
	private String comment;
	private List<String> days;
	private List<String> hours;
	
	public String getInstituteName() 					{ return instituteName; }
	public void setInstituteName(String instituteName) 	{ this.instituteName = instituteName; }
	public String getComment() 							{ return comment; }
	public void setComment(String comment) 				{ this.comment = comment; }
	
	public List<String> getDays()						{ return days; }
	public void setDays(List<String> days) 				{ this.days = days; }
	public List<String> getHours() 						{ return hours; }
	public void setHours(List<String> hours) 			{ this.hours = hours; }
}
