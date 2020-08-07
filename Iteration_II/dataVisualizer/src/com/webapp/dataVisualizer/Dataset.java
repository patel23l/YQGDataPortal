package com.webapp.dataVisualizer;

public class Dataset {
	//find a way to generalize this
	private String entry;
	
	public Dataset(String entry) {
		this.entry = entry;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}
}