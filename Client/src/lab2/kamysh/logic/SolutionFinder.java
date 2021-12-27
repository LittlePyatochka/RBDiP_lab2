package lab2.kamysh.logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SolutionFinder extends Remote {

    String findEquation(String id) throws RemoteException;

}