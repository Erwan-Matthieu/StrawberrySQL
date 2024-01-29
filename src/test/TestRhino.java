package test;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class TestRhino {

    public static String extractParentheses(String sentence) {
        return sentence;
    }

    public static double evaluate(String expression) {
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();

            Object result = cx.evaluateString(scope, expression, "<cmd>", 1, null);

            if (result instanceof Number) {
                return ((Number) result).doubleValue();
            } else {
                throw new IllegalArgumentException("Invalid result type");
            }
        } finally {
            Context.exit();
        }
    }

    public static void main(String[] args) {
        String newRowWithoutSpace = "3 * (4 + 5)";
        String operand = extractParentheses(newRowWithoutSpace);
        double result = evaluate(operand);
        System.out.println(result);
        String reString = String.valueOf(result);

        newRowWithoutSpace = newRowWithoutSpace.replaceAll(operand, reString);

        System.out.println("Result: " + newRowWithoutSpace);
    }
}
