package com.ulht.cd.project.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cenas {
	private Interface stub = null;
	protected void cenas(String s) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			stub = (Interface) registry.lookup(s);
		} catch (Exception e) {
			System.err.println("cenas erro");
		}
	}
	
	public String printDate() throws Exception {
		cenas("Hello");
		return stub.getDate();
	}
}
