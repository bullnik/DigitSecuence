import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        int[] sequence = new int[] { 9, 8, 7, 6, 5, 4, 3, 2 };
        int sum = 100;

        Expression exp = new Expression(sequence);
        ArrayList<Operations> operations = new ArrayList<>();
        search(exp, operations, sequence.length - 1, sum);
    }

    public static void search(Expression exp, ArrayList<Operations> operations, int length, int searchValue) {
        if (operations.size() == length) {
            Operations[] arr = new Operations[operations.size()];
            arr = operations.toArray(arr);
            exp.setOperations(arr);
            double sum = exp.solve();
            if (sum == searchValue) {
                System.out.println(exp.getExpression() + "=" + (int) sum);
            }
        } else {
            operations.add(Operations.PLUS);
            search(exp, operations, length, searchValue);
            operations.remove(operations.size() - 1);

            operations.add(Operations.MULTIPLY);
            search(exp, operations, length, searchValue);
            operations.remove(operations.size() - 1);

            operations.add(null);
            search(exp, operations, length, searchValue);
            operations.remove(operations.size() - 1);

            operations.add(Operations.MINUS);
            search(exp, operations, length, searchValue);
            operations.remove(operations.size() - 1);

            operations.add(Operations.DIVIDE);
            search(exp, operations, length, searchValue);
            operations.remove(operations.size() - 1);
        }
    }
}
