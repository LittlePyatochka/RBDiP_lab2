package lab2.kamysh;

import lab2.kamysh.logic.SolutionFinder;
import lab2.kamysh.logic.EquationSolver;
import lab2.kamysh.utils.ClientCode;
import lab2.kamysh.logic.LoginHandler;

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
    public static LoginHandler loginHandler;
    public static String login;


    public static void main(String[] args) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(Integer.parseInt(System.getenv("PORT")));
        solver = (EquationSolver) registry.lookup(ClientCode.BINDING_NAME_SOLVER);
        finder = (SolutionFinder) registry.lookup(ClientCode.BINDING_NAME_FINDER);
        loginHandler = (LoginHandler) registry.lookup(ClientCode.BINDING_NAME_LOGIN_HANDLER);

        welcomePage();
    }

    private static void welcomePage() throws RemoteException {
        while (true) {
            System.out.println("****************************************************************");
            System.out.println(ClientCode.LOGIN);
            System.out.println(ClientCode.ENTER_COMMAND);
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    if (loginPage()){
                        logicPage();
                    }
                    continue;
                case "2":
                    registryPage();
                    continue;
                case "3":
                    return;
                default:
                    System.out.println(ClientCode.INVALID_COMMAND);
                    break;
            }
        }
    }

    private static void registryPage(){
        String [] params = new String[2];
        for (int i = 0; i < 3; i++) {
            System.out.println("****************************************************************");
            System.out.println(ClientCode.WRITE_LOGIN);
            String login = scanner.nextLine();
            if (loginHandler.isLoginExist(login)) {
                System.out.println(ClientCode.WRITE_PASSWORD);
                String password = scanner.nextLine();
                params[0] = login;
                params[1] = password;
                if (loginHandler.registry(params)) {
                    System.out.println(ClientCode.REGISTRY_COMPLETED);
                    return;
                }
                System.out.println(ClientCode.REGISTRY_ERROR);
            } else {
                System.out.println(ClientCode.SUCH_LOGIN_ARE_ALREADY_EXIST);
            }
        }
    }

    private static boolean loginPage(){
        String [] params = new String[2];
        for (int i = 0; i < 3; i++){
            System.out.println("****************************************************************");
            System.out.println(ClientCode.WRITE_LOGIN);
            params [0] =  scanner.nextLine();
            System.out.println(ClientCode.WRITE_PASSWORD);
            params [1] = scanner.nextLine();
            if (loginHandler.login(params)){
                login = params [0];
                return true;
            }
            System.out.println(ClientCode.INVALID_LOGIN);
            System.out.println(ClientCode.TRY_AGAIN);
        }
        return false;
    }

    private static void logicPage() throws RemoteException {
        System.out.println("****************************************************************");
        System.out.println(ClientCode.LOGIN_SUCCESSFULLY_COMPLETED);

        while (true) {
            String command = scanner.nextLine();
            System.out.println("****************************************************************");
            System.out.println(ClientCode.WELCOME);
            System.out.println(ClientCode.ENTER_COMMAND);
            switch (scanArgument()) {
                case 1:
                    solveEquation();
                    continue;
                case 2:
                    System.out.println("Count solutions:" + finder.getCountSolutions(login));
                    System.out.println("Enter the index of the equation:");
                    System.out.println(finder.findSolution(scanArgument(), login));
                    continue;
                case 3:
                    return;
                default:
                    System.out.println(ClientCode.INVALID_COMMAND);
                    break;
            }
        }
    }

    public static void solveEquation() throws RemoteException {
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

    private static Integer scanArgument() {
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
        System.out.println(solver.solveEquation(1, eqParams, login));
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
        System.out.println(solver.solveEquation(2, eqParams, login));
    }

}
