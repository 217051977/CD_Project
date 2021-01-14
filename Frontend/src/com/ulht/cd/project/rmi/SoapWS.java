package com.ulht.cd.project.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.jws.WebService;

@WebService(targetNamespace = "http://rmi.project.cd.ulht.com/", portName = "SoapWSPort", serviceName = "SoapWSService")
public class SoapWS {
	private Interface stub = null;
	
	private void newStub(String name) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			try {
				stub = (Interface) registry.lookup(name);
			} catch (NotBoundException e) {
				System.err.println("There is no registry with the name " + name + "!");
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			System.err.println("Unable to connect to backend server!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String printDate() throws Exception {
		newStub("Hello");
		return "Hi " + stub.getDate();
	}
	
}
