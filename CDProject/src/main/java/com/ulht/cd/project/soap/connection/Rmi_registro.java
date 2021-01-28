package com.ulht.cd.project.soap.connection;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Rmi_registro {
	private Interface stub = null;
	protected void registroRmi(String s) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			stub = (Interface) registry.lookup(s);
		} catch (Exception e) {
			System.err.println("registro erro");
		}
	}
	
	public String printDate() throws Exception {
		registroRmi("Hello");
		return stub.getDate();
	}
}

