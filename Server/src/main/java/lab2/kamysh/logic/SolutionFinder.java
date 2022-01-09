package lab2.kamysh.logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SolutionFinder extends Remote {

    String findSolution(String id) throws RemoteException;
}
