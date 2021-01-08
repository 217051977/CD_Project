package com.ulht.cd.project.db;

import java.net.URL;
//import java.time.Instant;
import java.util.List;
import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.NoDocumentException;
import com.cloudant.client.org.lightcouch.PreconditionFailedException;
import com.ulht.cd.project.db.FileManager.FileItem;
import com.ulht.cd.project.db.FileManager.FileResume;

public class DB {

	//private Instant currentTime;
	private static CloudantClient client;
	private Database db;
	
	private String localUrl = "http://localhost:5984";
	private String localUsername = "admin";
	private String localPassword = "admin";
	
	private List<String> clientDatabases;
	
	public DB() throws Exception {
		try {
			client = ClientBuilder.url(new URL(localUrl)).username(localUsername).password(localPassword).build();
		} catch (Exception e) {
			System.out.println("Server not available");
		}
	}
	
	public DB(String url, String username, String password) throws Exception {
		try {
			client = ClientBuilder.url(new URL(url)).username(username).password(password).build();
		} catch (Exception e) {
			System.out.println("Server not available");
		}
	}
	
	public void getClientDatabases() {
		clientDatabases = client.getAllDbs();
	}
	
	public void printClientDatabases() {
		System.out.println(clientDatabases);
		
		for (String database : clientDatabases) {
			System.out.println(database);
		}		
	}
	
	public void createDatabase(String name) {
		try {
			client.createDB(name);
			createFileResumeInDatabase(name);
		} catch(PreconditionFailedException sameName) {
			System.out.println("There is alredy a database with the name " + name);
		}
	}
	
	public void deleteDatabase(String name) {
		boolean found = false;
		getClientDatabases();
		for (String database : clientDatabases) {
			if(database.equals(name)) {
				found = true;
				break;
			}
		}
		if (found) {
			client.deleteDB(name);
		} else {
			System.out.println("There is no database called " + name);
		}
	}
	
	public void getAllDoc(String dataBaseName) {
		createDatabaseInstance(dataBaseName);
		boolean outOfBounds = false;
		int i = 0;
		
		try {
			while (!outOfBounds) {
				if ( i != 0) {
					System.out.println((FileItem) db.find(FileItem.class, "" + i));
				} else {
					System.out.println((FileResume) db.find(FileResume.class, "" + i));
				}
				i++;
			}
			
		} catch (Exception e) {
			outOfBounds = true;
		}
	}
	
	public void getVotingItems(String dataBaseName) {
		createDatabaseInstance(dataBaseName);
		boolean outOfBounds = false;
		int i = 0;
		FileItem fileItem;
		try {
			while (!outOfBounds) {
				if ( i != 0) {
					
					fileItem = (FileItem) db.find(FileItem.class, "" + i);
					System.out.println("(" + fileItem.get_id() + ") " + fileItem.getName());
				}
				i++;
			}
			
		} catch (Exception e) {
			outOfBounds = true;
		}
	}
	
	public FileItem getDocByName(String dataBaseName, String docName) {
		createDatabaseInstance(dataBaseName);
		boolean outOfBounds = false;
		int i = 1;
		
		try {
			while (!outOfBounds) {
				FileItem fileItem = db.find(FileItem.class, "" + i);
				if (fileItem.getName().equals(docName)) {
					return fileItem;
				}
				i++;
			}
			return null;
			
		} catch (Exception e) {
			outOfBounds = true;
			System.out.println("Could not found that document");
			return null;
		}
	}
	
	public void createItemInDatabase(String dataBaseName, Object obj) {
		
		try {
			createDatabaseInstance(dataBaseName);
			FileItem fi = (FileItem) obj;
			
			fi.setID();
			
			db.save(fi);

			FileResume fir = (FileResume) findFileResume("0");
			fir.addItem();
			db.update(fir);
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void ddeleteItemFromDatabase(String dataBaseName, Object obj) {
		
		try {
			createDatabaseInstance(dataBaseName);
			
			db.remove(obj);

			FileResume fir = (FileResume) findFileResume("0");
			fir.removeItem();
			db.update(fir);
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public Object findFileItem(String id) {
		try {
			if (id.equals("0")) {
				System.out.println("There is no File Item on the index \"0\"");
				return null;
			}
			return db.find(FileItem.class, id);
		} catch (NoDocumentException missingDocument) {
			System.out.println("Could not found that document");
			return null;
		}
	}
	
	public Object findFileItemByName(String dataBaseName, String docName) {
		FileItem fi = getDocByName(dataBaseName, docName);
		if (fi != null) {
			return fi;
		}
		return null;
	}
	
	public Object findFileResume(String id) {
		try {
			if (!id.equals("0")) {
				System.out.println("The File Resume is on the index \"0\"");
				return null;
			}
			return db.find(FileResume.class, id);
		} catch (NoDocumentException missingDocument) {
			System.out.println("Could not found that document");
			return null;
		}
	}
	
	private void createDatabaseInstance(String dataBaseName) {
		
		db = client.database(dataBaseName, false);
		
	}
	
	private void createFileResumeInDatabase(String name) {
		createDatabaseInstance(name);
		FileResume fr = new FileResume();
		fr.setID();
		db.save(fr);
	}
	
	public void voteItem(String dataBaseName, String itemId) {
		createDatabaseInstance(dataBaseName);
		
		FileItem fi = (FileItem) findFileItem(itemId);
		
		fi.vote();
		
		db.update(fi);
		
	}

	public void voteItemByName(String dataBaseName, String docName) {
		createDatabaseInstance(dataBaseName);
		
		FileItem fi = (FileItem) findFileItemByName(dataBaseName, docName);
		
		fi.vote();
		
		db.update(fi);
		
	}
	
	public int getTotalNumberOfVotes(String dataBaseName) {
		createDatabaseInstance(dataBaseName);
		boolean outOfBounds = false;
		int i = 0;
		int voteCounting = 0;
		FileItem fileItem;
		try {
			while (!outOfBounds) {
				if ( i != 0) {
					fileItem = (FileItem) db.find(FileItem.class, "" + i);
					voteCounting += fileItem.getVoteCounter();
				}
				i++;
			}
			
		} catch (Exception e) {
			outOfBounds = true;
		}
		return voteCounting;
	}

	public String getWinningItem(String dataBaseName) {
		createDatabaseInstance(dataBaseName);
		boolean outOfBounds = false;
		int i = 0;
		int winnigItemVoteCounting = 0;
		int itemVoteCounting = 0;
		String winnigItemName = "";
		FileItem fileItem;
		try {
			while (!outOfBounds) {
				if ( i != 0) {
					fileItem = (FileItem) db.find(FileItem.class, "" + i);
					itemVoteCounting = fileItem.getVoteCounter();
					if (winnigItemVoteCounting < itemVoteCounting) {
						winnigItemVoteCounting = itemVoteCounting;
						winnigItemName = fileItem.getName();
					}
				}
				i++;
			}
		} catch (Exception e) {
			outOfBounds = true;
		}
		return winnigItemName;
	}
	
	public String getItemName(String dataBaseName, String id) {
		createDatabaseInstance(dataBaseName);
		boolean outOfBounds = false;
		int i = 0;
		FileItem fileItem;
		try {
			while (!outOfBounds) {
				if ( i != 0) {
					fileItem = (FileItem) db.find(FileItem.class, "" + i);
					if (fileItem.get_id().equals(id)) {
						return fileItem.getName();
					}
				}
				i++;
			}
		} catch (Exception e) {
			outOfBounds = true;
		}
		return null;
	}
	
}
