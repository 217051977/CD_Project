package maven.example.cdproject;

import java.net.URL;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

public class DeleteDB {
	
	public static void main( String[] args ) throws Exception {
    	
    	CloudantClient client =
    	ClientBuilder.url(new URL("http://localhost:5984"))
    	              .username("admin")
    	              .password("admin")
    	              .build();
    	
    	client.deleteDB("example_db");
    	
    }


}
