package com.ulht.cd.project.db.FileManager;

public class FileItem extends FileBase {

	private String name;
	private int voteCounter;
	private int totalTime;
	private int timeLeft;
	private boolean active;
	
	public FileItem(String name) {
		this.name = name;
		this.voteCounter = 0;
	}
	
	public FileItem(String name, int totalTime) {
		this.name = name;
		this.totalTime = totalTime;
		this.voteCounter = 0;
		this.active = false;
	}

	public int getVoteCounter() {
		return voteCounter;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void changeName(String name) {
		this.name = name;
	}
	
	public void vote() {
		voteCounter++;
	}
	
	public void setID() {
		super.nextID();
	}
	
	@Override
	public String toString() {
		return "_id = " + super.get_id() + 
			   "\n_rev = " + super.get_rev() + 
			   "\nname = " + this.name + 
			   "\nvoteCounter = " + this.voteCounter;
	}
	
}
