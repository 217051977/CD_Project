package com.ulht.cd.project.rmi;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class Server implements Interface{
	
	public Server() {}
	
	public String getDate() {
		Date date = new Date();
		return "Server " + date.toString();
	}
	
	private static Registry newRegistry(int port) throws RemoteException {
		LocateRegistry.createRegistry(port);
		Registry reg = LocateRegistry.getRegistry(port);
		return reg;
	}
	
	public static void main(String[] args) {
		int registryPort = 1099;
		try {
			Server obj = new Server();
			Interface stub = (Interface) UnicastRemoteObject.exportObject(obj, 0);
			
			Registry registry = newRegistry(registryPort);
			registry.bind("Hello", stub);
			
			System.err.println("Server ready");
		}catch (Exception e) {
			System.err.println("Server exceptio: " + e.toString());
			e.printStackTrace();
		}
	}

}
