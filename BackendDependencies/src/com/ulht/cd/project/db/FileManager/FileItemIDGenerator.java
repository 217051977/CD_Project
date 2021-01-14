package com.ulht.cd.project.db.FileManager;

public final class FileItemIDGenerator {
	
	private static int id;
	
	private FileItemIDGenerator() {}
	
	public static Integer getNextId() {
		id = id + 1;
		return id;
	}
	
	public static int checkCurrentId() {
		return id;
	}
	
	public static Integer startId() {
		id = 0;
		return id;
	}

}
