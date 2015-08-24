package org.confetti.rcp.wizards.models;

import static org.confetti.rcp.ConfettiPlugin.IMG_BIG_INSTITUTE;

public class EditInstituteModel implements EditNameAndCommentModel {
	private String instituteName;
	private String comment;
	
	public EditInstituteModel(final String instituteName, final String comment) {
		this.instituteName = instituteName;
		this.comment = comment;
	}

	@Override public String getName() 						{ return instituteName; }
	@Override public void setName(String name) 				{ this.instituteName = name; }
	@Override public String getComment() 					{ return comment; }
	@Override public void setComment(String comment) 		{ this.comment = comment; }
	@Override public String getImageKey() 					{ return IMG_BIG_INSTITUTE; }
}
