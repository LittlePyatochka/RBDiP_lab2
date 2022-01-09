package lab2.kamysh.logic;

import lab2.kamysh.utils.FileHelper;
import lab2.kamysh.utils.ServerCode;

import java.io.IOException;
import java.rmi.RemoteException;


public class SolutionFinderImpl implements SolutionFinder {

    @Override
    public String findSolution(String id) throws RemoteException {
        try {
            return  FileHelper.getSolveById(id);
        } catch (IOException ex) {
            return ServerCode.ERROR_READING;
        }
    }

}
