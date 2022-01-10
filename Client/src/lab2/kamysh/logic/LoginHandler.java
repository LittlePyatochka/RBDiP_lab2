package lab2.kamysh.logic;

import java.rmi.Remote;

public interface LoginHandler extends Remote {

    boolean registry(String[] params);
    boolean login(String[] params);
    boolean isLoginExist(String login);
}
