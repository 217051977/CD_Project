package com.ulht.cd.project.rmi;

import java.rmi.RemoteException; import java.rmi.Remote;

public interface Interface extends Remote { public String getDate() throws
RemoteException; }
 