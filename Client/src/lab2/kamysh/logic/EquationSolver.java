package lab2.kamysh.logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EquationSolver extends Remote {

    String solveEquation(int equationType, int[] params) throws RemoteException;

}
