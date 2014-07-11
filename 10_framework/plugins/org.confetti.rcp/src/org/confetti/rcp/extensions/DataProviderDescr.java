package org.confetti.rcp.extensions;

public class DataProviderDescr {

	private String name;

	public DataProviderDescr(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
