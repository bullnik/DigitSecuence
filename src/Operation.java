import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Operation implements ExpressionElement {
    public Operations value;

    public Operation(Operations op) {
        value = op;
    }

    public int getPriority() {
        switch (value) {
            case PLUS:
            case MINUS:
                return 0;
            case DIVIDE:
            case MULTIPLY:
                return 1;
            default:
                throw new NotImplementedException();
        }
    }

    public double getResult(double a, double b) {
        switch (value) {
            case PLUS:
                return a + b;
            case MINUS:
                return a - b;
            case DIVIDE:
                return a / b;
            case MULTIPLY:
                return a * b;
            default:
                throw new NotImplementedException();
        }
    }

    @Override
    public String toString() {
        switch (value) {
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case DIVIDE:
                return "/";
            case MULTIPLY:
                return "*";
            default:
                throw new NotImplementedException();
        }
    }

    @Override
    public String getType() {
        return "operation";
    }
}
