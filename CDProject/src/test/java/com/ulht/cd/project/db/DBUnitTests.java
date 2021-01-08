package com.ulht.cd.project.db;

//import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ulht.cd.project.db.FileManager.FileItem;

public class DBUnitTests {

	/*@Test
	public void getClientDatabases() throws Exception {
		DB db = new DB();

		System.err.println("GETTING DATABASES");
		db.getClientDatabases();

		System.err.println("PRINTING DATABASES");
		db.printClientDatabases();

		System.err.println("\nDONE");
	}*/

	/*@Test
	public void deleteDatabase() throws Exception {
		DB db = new DB();

		System.err.println("DELETING DATABASE \"test_database\"");
		db.deleteDatabase("test_database");
		System.err.println("\nDONE");
		
	}

	@Test
	public void createDatabase() throws Exception {
		DB db = new DB();

		System.err.println("CREATING DATABASE \"test_database\"");
		db.createDatabase("test_database");
		System.err.println("\nDONE");
	}

	@Test
	public void createItemInDatabase() throws Exception {
		DB db = new DB();

		System.err.println("DELETING DATABASE \"test_database\"");
		db.deleteDatabase("test_database");

		System.err.println("CREATING DATABASE \"test_database\"");
		db.createDatabase("test_database");
		
		db.createItemInDatabase("test_database", new FileItem("ItemTest"));
		
		db.createItemInDatabase("test_database", new FileItem("ItemTest2"));
		
		System.err.println("\nDONE");
	}*/

	/*@Test
	public void createDatabaseWithItem() throws Exception {
		DB db = new DB();
		
		System.err.println("RESETING DATABASE \"test_database\"");
		db.deleteDatabase("test_database");

		System.err.println("CREATING DATABASE \"test_database\"");
		db.createDatabase("test_database");
		
		db.createItemInDatabase("test_database", new FileItem("ItemTest"));
		
		db.createItemInDatabase("test_database", new FileItem("ItemTest2"));
		
		System.err.println("\nDONE");
	}*/
	
	/*@Test
	public void findFileItemResume() throws Exception {
		
		DB db = new DB();
		
		System.err.println("RESETING DATABASE \"test_database_find\"");
		db.deleteDatabase("test_database_find");
		
		System.err.println("CREATING DATABASE \"test_database_find\"");
		db.createDatabase("test_database_find");
		
		Object doc = db.find("0");

		System.out.println((FileItemResumeTest) doc);
		
		System.err.println("\nDONE");
	}
	
	/*@Test
	public void findFileItemMissingID() throws Exception {
		
		DB db = new DB();
		
		System.err.println("RESETING DATABASE \"test_database_find_missing_id\"");
		db.deleteDatabase("test_database_find_missing_id");
		
		System.err.println("CREATING DATABASE \"test_database_find_missing_id\"");
		db.createDatabase("test_database_find_missing_id");
		
		System.err.println("CREATING FILE ITEM \"ItemTest2\"");
		db.createItemInDatabase("test_database_find_missing_id", new FileItem("ItemTest2"));
		
		Object fileItemId0 = db.findFileItem("0");
		Object fileResumeId1 = db.findFileResume("1");
		Object fileItemId3 = db.findFileItem("3");
		
		if (fileItemId0 != null) {
			System.out.println((FileItem) fileItemId0);
		}
		
		if (fileResumeId1 != null) {
			System.out.println((FileResume) fileResumeId1);
		}
		
		assertEquals("Not supose to find a File Resume as a File Item", null, fileItemId0);
		assertEquals("Not supose to find a File Item as a File Resume", null, fileResumeId1);
		assertEquals("There is no File Item with the id \"3\"", null, fileItemId3);
		
		System.err.println("\nDONE");
	}*/
	
	/*@Test
	public void deleteFileItemFromDatabase() throws Exception {
		
		DB db = new DB();

		System.err.println("GETTING FILE ITEM \"ItemTest2\"");
		FileItem fi = (FileItem) db.findFileItem("ItemTest2");
		
		System.err.println("DELITING FILE ITEM \"ItemTest2\"");
		db.ddeleteItemFromDatabase("test_database", fi);

		System.err.println("GETTING FILE ITEM \"ItemTest2\"");
		fi = (FileItem) db.findFileItem("ItemTest2");
		
		assertEquals("Not supose to find a File Resume as a File Item", null, fi);
		
		System.err.println("\nDONE");
	}*/
	
	/*@Test
	public void deleteFileItemFromDatabase() throws Exception {
		
		DB db = new DB();

		System.err.println("RESETING DATABASE \"test_database\"");
		db.deleteDatabase("test_database");

		System.err.println("CREATING DATABASE \"test_database\"");
		db.createDatabase("test_database");

		System.err.println("CREATING FILE ITEM \"ItemTest\"");
		db.createItemInDatabase("test_database", new FileItem("ItemTest"));
		System.err.println("CREATING FILE ITEM \"ItemTest2\"");
		db.createItemInDatabase("test_database", new FileItem("ItemTest2"));
		
		FileItem fi = db.getDocByName("test_database", "ItemTest");
		db.ddeleteItemFromDatabase("test_database", fi);
		
		System.out.println(fi);
		
		System.err.println("\nDONE");
	}*/
	
	@Test
	public void vote() throws Exception {
		DB db = new DB();

		System.err.println("RESETING DATABASE \"test_database\"");
		db.deleteDatabase("test_database");
		db.createDatabase("test_database");

		System.err.println("CREATING FILE ITEM \"ItemTest\"");
		db.createItemInDatabase("test_database", new FileItem("ItemTest"));
		System.err.println("CREATING FILE ITEM \"ItemTest2\"");
		db.createItemInDatabase("test_database", new FileItem("ItemTest2"));

		System.err.println("VOTING ON FILE ITEM \"ItemTest\"");
		db.voteItem("test_database", "1");

		System.err.println("VOTING BY NAME x2 ON FILE ITEM \"ItemTest2\"");
		db.voteItemByName("test_database", "ItemTest2");
		db.voteItemByName("test_database", "ItemTest2");
		
		System.err.println("\nDONE");
	}
	
}
