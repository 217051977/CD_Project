package com.ulht.cd.project.soap.connection;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	
	private Client() {}
	
	public static void main(String[] args) {
		
		String host = "localhost"/*(args.length < 1) ? null : args[0]*/;
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			Interface stub = (Interface) registry.lookup("Hello");
			String response = stub.getDate();
			System.out.println(response);
		} catch (Exception e) {
			System.err.println("Server exceptio: " + e.toString());
			e.printStackTrace();
		}
		
	}
	
}
