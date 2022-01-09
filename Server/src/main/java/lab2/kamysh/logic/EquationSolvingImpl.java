package lab2.kamysh.logic;

import lab2.kamysh.equations.SimpleEquation;
import lab2.kamysh.equations.SquareEquation;
import lab2.kamysh.utils.FileHelper;
import lab2.kamysh.utils.ServerCode;

import java.io.IOException;


public class EquationSolvingImpl implements EquationSolver {

    @Override
    public String solveEquation(int equationType, int[] params) throws IOException {

        String solution;

        switch (equationType) {
            case 1:
                SimpleEquation simple = new SimpleEquation(params[0], params[1]);
                solution = String.format(ServerCode.WRITE_SIMPLE_EQUATION, simple.getK(), simple.getB(), simple.getResult());
                break;
            case 2:
                SquareEquation square = new SquareEquation(params[0], params[1], params[2]);
                int[] res = square.getResult();
                solution = String.format(ServerCode.WRITE_SQUARE_EQUATION, params[0], params[1], params[2], res[0], res[1]);
                break;
            default:
                System.out.println(ServerCode.INVALID_TYPE);
                return null;
        }

        //FileHelper.saveSolution(solution);

        return solution;
    }

}
