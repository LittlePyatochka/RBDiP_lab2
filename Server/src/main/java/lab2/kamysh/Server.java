package lab2.kamysh;

import lab2.kamysh.entity.SessionFactoryBuilder;
import lab2.kamysh.logic.EquationSolvingImpl;
import lab2.kamysh.logic.LoginHandler;
import lab2.kamysh.logic.LoginHandlerImpl;
import lab2.kamysh.logic.SolutionFinderImpl;
import lab2.kamysh.utils.ServerCode;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {


    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        final EquationSolvingImpl solvingServer = new EquationSolvingImpl();
        final SolutionFinderImpl findingServer = new SolutionFinderImpl();
        final LoginHandlerImpl loginHandler = new LoginHandlerImpl();
        final Registry registry = LocateRegistry.createRegistry(Integer.parseInt(System.getenv("PORT")));
        Remote stub = UnicastRemoteObject.exportObject(solvingServer, 0);
        Remote stubFind = UnicastRemoteObject.exportObject(findingServer, 1);
        Remote login = UnicastRemoteObject.exportObject(loginHandler, 2);

        registry.bind(ServerCode.BINDING_NAME_SOLVER, stub);
        registry.bind(ServerCode.BINDING_NAME_FINDER, stubFind);
        registry.bind(ServerCode.BINDING_NAME_LOGIN_HANDLER, login);

        System.out.println(ServerCode.START);

        Thread.sleep(Integer.MAX_VALUE);
    }

}
