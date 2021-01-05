package maven.example.cdproject;

public class LDAPTest {
	
	public static void main(String[] args) throws Exception {

		String user_id = "2233322"; // user id
		String user_pwd = "qwerty"; // user password
		String base = "ou=dev,dc=example,dc=com";
		String user_rdn ="ou=people"; // user RDN (relative distinguished names)
		String dn = "uid=" + user_id + "," + user_rdn + "," + base; // DN (distinguished name)
		
		// validate dn
		if (LDAPUtils.validDn(dn)) {
			System.out.println("DN: true");
		}	
		else {
			LDAPUtils.closeConnection();
			System.out.println("DN: false");
			System.exit(-1);
		}

		// auth
		if (LDAPUtils.auth(dn, user_pwd)) {
			System.out.println("AUTH: true");
		}	
		else {
			System.out.println("AUTH: false");
		} 
		
		// print attributes
		LDAPUtils.printDnAttributes(dn); 
		
		// unbind connection
		LDAPUtils.unBindConnection();
		
		// test search
		LDAPUtils.testSearch(user_rdn + "," + base);
		
		System.exit(0);
	}
	
}
