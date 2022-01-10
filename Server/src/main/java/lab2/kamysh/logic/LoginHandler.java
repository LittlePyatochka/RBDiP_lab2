package lab2.kamysh.logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginHandler extends Remote {

    boolean registry(String[] params) throws RemoteException;
    boolean login(String[] params)throws RemoteException;
    boolean isLoginExist(String login)throws RemoteException;
}
