package lab2.kamysh.logic;

import java.io.IOException;
import java.rmi.Remote;

public interface EquationSolver extends Remote {

    String solveEquation(int equationType, int[] params) throws IOException;

}
