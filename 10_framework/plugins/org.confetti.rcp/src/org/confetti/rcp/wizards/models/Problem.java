package org.confetti.rcp.wizards.models;

public enum Problem {
	OK("OK"), 
	ALREADY_EXISTS("Already exists"), 
	DUPLICATED_NAME("Duplicated name");

	private String mDesc;

	Problem(String desc) {
		mDesc = desc;
	}

	public String getDescription() {
		return mDesc;
	}
}