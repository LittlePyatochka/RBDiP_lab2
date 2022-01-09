package lab2.kamysh.equations;

public class SquareEquation {

    private final int a;
    private final int b;
    private final int c;

    public SquareEquation(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {return a;}
    public int getB() {return b;}
    public int getC() {return c;}

    public int[] getResult() {
        int[] result = new int[2];

        if (a == 0) {
            result[0] = -c / b;
            result[1] = -c / b;
            return result;
        }

        int discriminant = (int) (Math.pow(b, 2) - 4 * a * c);
        result[0] = (int) ((-b - Math.sqrt(discriminant)) / (2 * a));
        result[1] = (int) ((-b + Math.sqrt(discriminant)) / (2 * a));
        return result;
    }
}
