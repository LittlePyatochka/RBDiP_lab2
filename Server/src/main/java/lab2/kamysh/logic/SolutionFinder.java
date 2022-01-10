package lab2.kamysh.logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SolutionFinder extends Remote {

    String findSolution(Integer index, String login) throws RemoteException;
    Long getCountSolutions(String login) throws RemoteException;
}
