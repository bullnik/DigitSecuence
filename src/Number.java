public class Number implements ExpressionElement {
    public double value;

    public Number(double num) {
        value = num;
    }

    @Override
    public String getType() {
        return "number";
    }

    @Override
    public String toString() {
        return Integer.toString((int) value);
    }
}
