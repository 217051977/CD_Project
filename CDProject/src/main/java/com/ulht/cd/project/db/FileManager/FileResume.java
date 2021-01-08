package com.ulht.cd.project.db.FileManager;

public class FileResume extends FileBase {
	
	private int itemCounter;
	
	public FileResume() {
		
		itemCounter = 0;
		
	}
	
	public void addItem() {
		
		itemCounter++;
		
	}
	
	public void removeItem() {
		
		itemCounter--;
		
	}
	
	public void setID() {
		super.startIDCounter();
	}
	
	@Override
	public String toString() {
		return "_id = " + super.get_id() + 
			   "\n_rev = " + super.get_rev() + 
			   "\nitemCounter = " + this.itemCounter;
	}

}
