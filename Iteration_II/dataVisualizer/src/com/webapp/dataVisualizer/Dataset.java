package com.webapp.dataVisualizer;

public class Dataset {
	//find a way to generalize this
	private int ward;
	private String name;
	private int votes;
	
	public Dataset(int ward, String name, int votes) {
		this.ward = ward;
		this.name = name;
		this.votes = votes;
	}

	public int getWard() {
		return ward;
	}

	public void setWard(int ward) {
		this.ward = ward;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}
	
}
