package lab2.kamysh.equations;

public class SimpleEquation {

    private final int k;
    private final int b;

    public SimpleEquation(int k, int b) {
        this.k = k;
        this.b = b;
    }
    public int getResult(){
        return - b / k;
    }
    public int getB() {
        return b;
    }
    public int getK() {
        return k;
    }
}
