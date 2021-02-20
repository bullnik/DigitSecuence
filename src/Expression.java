import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Stack;

public class Expression {
    private Number[] numbers;
    private Operation[] operations;
    private ArrayList<ExpressionElement> expression;

    public Expression(int[] sequence) {
        if (sequence.length < 2) {
            throw new NotImplementedException();
        }

        operations = new Operation[sequence.length - 1];
        initNumbers(sequence);
    }

    public double solve() {
        expression = getExp();
        ArrayList<ExpressionElement> postfixExp = infixToPostfix();
        return getValueFromPostfix(postfixExp);
    }

    private ArrayList<ExpressionElement> infixToPostfix() {
        ArrayList<ExpressionElement> postfixExp = new ArrayList<ExpressionElement>();
        Stack<ExpressionElement> stack = new Stack<>();

        for (ExpressionElement el : expression) {

            if (el.getType().equals("number")) {
                postfixExp.add(el);
            } else if (el.getType().equals("operation")) {
                int currentPriority = ((Operation) el).getPriority();
                while (!stack.isEmpty()
                        && ((Operation) stack.peek()).getPriority() >= currentPriority) {
                    postfixExp.add(stack.pop());
                }
                stack.push(el);
            } else {
                throw new NotImplementedException();
            }
        }
        while (!stack.isEmpty()) {
            postfixExp.add(stack.pop());
        }

        return postfixExp;
    }

    private double getValueFromPostfix(ArrayList<ExpressionElement> postfixExp) {
        Stack<ExpressionElement> stack = new Stack<>();

        for (ExpressionElement el : postfixExp) {
            if (el.getType().equals("number")) {
                stack.push(el);
            } else if (el.getType().equals("operation")) {
                double num1 = ((Number) stack.pop()).value;
                double num2 = ((Number) stack.pop()).value;
                stack.push(new Number(((Operation) el).getResult(num2, num1)));
            } else {
                throw new NotImplementedException();
            }
        }

        return ((Number) stack.peek()).value;
    }

    private ArrayList<ExpressionElement> getExp() {
        ArrayList<ExpressionElement> exp = new ArrayList<>();
        String lastDigits = Integer.toString((int) numbers[0].value);

        for (int i = 0; i < operations.length; i++) {
            if (operations[i] != null) {
                exp.add(new Number(Integer.parseInt(lastDigits)));
                exp.add(operations[i]);
                lastDigits = Integer.toString((int) numbers[i + 1].value);
            } else {
                lastDigits += Integer.toString((int) numbers[i + 1].value);
            }
        }
        exp.add(new Number(Double.parseDouble(lastDigits)));

        return exp;
    }

    public String getExpression() {
        if (expression == null) {
            throw new NotImplementedException();
        }

        String output = "";
        for (ExpressionElement el : expression) {
            output += el.toString();
        }
        return output;
    }

    public void setOperations(Operations[] ops) {
        if (ops.length != numbers.length - 1) {
            throw new NotImplementedException();
        }

        operations = new Operation[ops.length];
        for (int i = 0; i < ops.length; i++) {
            if (ops[i] == null) {
                operations[i] = null;
            } else {
                operations[i] = new Operation(ops[i]);
            }
        }
    }

    private void initNumbers(int[] sequence) {
        numbers = new Number[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            numbers[i] = new Number(sequence[i]);
        }
    }
}
