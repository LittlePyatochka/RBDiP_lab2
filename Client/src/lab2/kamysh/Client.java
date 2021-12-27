package lab2.kamysh;

import lab2.kamysh.logic.SolutionFinder;
import lab2.kamysh.logic.EquationSolver;
import lab2.kamysh.utils.ClientCode;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Client {
    static Scanner scanner = new Scanner(System.in);
    public static EquationSolver solver;
    public static SolutionFinder finder;

    public static void main(String[] args) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(Integer.parseInt(System.getenv("PORT")));
        solver = (EquationSolver) registry.lookup(ClientCode.BINDING_NAME_SOLVER);
        finder = (SolutionFinder) registry.lookup(ClientCode.BINDING_NAME_FINDER);


        while (true) {
            System.out.println("****************************************************************");
            System.out.println(ClientCode.WELCOME);
            System.out.println(ClientCode.ENTER_COMMAND);
            String command = scanner.nextLine();
            switch (command) {
                case "solve":
                    solveEquation();
                    break;
                case "get solve":
                    System.out.println("Enter the index of the equation:");
                    System.out.println(finder.findEquation(scanner.nextLine()));
                    break;
                case "quit":
                    return;
                default:
                    System.out.println(ClientCode.INVALID_COMMAND);
                    break;
            }
        }
    }

    private static void solveEquation() throws RemoteException {
        System.out.println(ClientCode.TYPES_EQUATION);
        while (true) {
            switch (scanArgument()) {
                case 1:
                    solveSimpleEquation();
                    return;
                case 2:
                    solveSquareEquation();
                    return;
                case 3:
                    return;
                default:
                    System.out.println(ClientCode.INVALID_TYPE);
            }
        }
    }

    private static int scanArgument() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ClientCode.WRONG_ARG);
            }
        }
    }

    private static void solveSimpleEquation() throws RemoteException {
        int[] eqParams = new int[2];
        System.out.println(ClientCode.ENTER_PARAM);
        System.out.println("Parameter k:");
        eqParams[0] = scanArgument();
        System.out.println("Parameter b:");
        eqParams[1] = scanArgument();
        System.out.println(solver.solveEquation(1, eqParams));
    }

    private static void solveSquareEquation() throws RemoteException {
        int[] eqParams = new int[3];
        System.out.println(ClientCode.ENTER_PARAM);
        System.out.println("Parameter a:");
        eqParams[0] = scanArgument();
        System.out.println("Parameter b:");
        eqParams[1] = scanArgument();
        System.out.println("Parameter c:");
        eqParams[2] = scanArgument();
        System.out.println(solver.solveEquation(2, eqParams));
    }

}
