package lab2.kamysh;

import lab2.kamysh.logic.EquationSolvingImpl;
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
        final Registry registry = LocateRegistry.createRegistry(Integer.parseInt(System.getenv("PORT")));

        Remote stub = UnicastRemoteObject.exportObject(solvingServer, 0);
        Remote stubFind = UnicastRemoteObject.exportObject(findingServer, 1);

        registry.bind(ServerCode.BINDING_NAME_SOLVER, stub);
        registry.bind(ServerCode.BINDING_NAME_FINDER, stubFind);

        System.out.println(ServerCode.START);

        Thread.sleep(Integer.MAX_VALUE);
    }

}
