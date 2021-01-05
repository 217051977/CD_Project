package maven.example.cdproject;

import java.net.URL;
import java.util.List;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

public class App {
    public static void main( String[] args ) throws Exception {
    	
    	CloudantClient client =
    	ClientBuilder.url(new URL("http://localhost:5984"))
    	              .username("admin")
    	              .password("admin")
    	              .build();

    	// Show the server version
    	System.out.println("Server Version: " + client.serverVersion());

    	// Get a List of all the databases this Cloudant account
    	List<String> databases = client.getAllDbs();
    	System.out.println("All my databases : ");
    	for ( String db : databases ) {
    		System.out.println(db);
    	}

    	// Working with data

    	// Create a new database.
    	client.createDB("example_db");

    	// Get a Database instance to interact with, but don't create it if it doesn't already exist
    	Database db = client.database("example_db", false);

    	// Create an ExampleDocument and save it in the database
    	db.save(new ExampleDocument(true, "teste1"));
    	System.out.println("You have inserted the document");

    	// Get an ExampleDocument out of the database and deserialize the JSON into a Java type
    	ExampleDocument doc = db.find(ExampleDocument.class,"example_id");
    	System.out.println(doc);
    	
    	// Delete a database we created previously.
    	//client.deleteDB("example_db");
    	
    }
}
