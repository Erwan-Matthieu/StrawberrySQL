package function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExtractParentheses {
    public static int countParentheses(String sentence, char target) {
        int count = 0;
        char[] sentences = sentence.toCharArray();

        for (char c : sentences) {
            if (c == target) {
                count++;
            }
        }
        return count;
    }
    public static String extractParentheses(String sentence) {
        String patternString = "\\(.*?\\)";
        
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(sentence);

        if (matcher.find()) {
            return matcher.group(0);    
        }

        return null;
    }

    public static String executeParentheses(String sentence) {
        sentence = sentence.replace("(", "");
        sentence = sentence.replace(")", "");

        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");

            if (engine != null) {
                return (String) engine.eval(sentence);
            } else {
                System.out.println("ScriptEngine is null. Unable to evaluate the expression.");
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static double evaluate(String input) {
        input = input.replace("(", "");
        input = input.replace(")", "");

        String[] parts = input.split("[\\+\\-\\*/]");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid expression");
        }

        double operand1 = Double.parseDouble(parts[0].trim());
        double operand2 = Double.parseDouble(parts[1].trim());

        double result;

        if (input.contains("+")) {
            result = operand1 + operand2;
        } else if (input.contains("-")) {
            result = operand1 - operand2;
        } else if (input.contains("*")) {
            result = operand1 * operand2;
        } else if (input.contains("/")) {
            if (operand2 == 0) {
                throw new ArithmeticException("Division by zero");
            }
            result = operand1 / operand2;
        } else {
            throw new IllegalArgumentException("Invalid expression");
        }

        return result;
    }

    public static boolean isInteger(double value) {
        return value % 1 == 0;
    }

    public static void main(String[] args) {
        String input = ExtractParentheses.extractParentheses("[a,(2-(1/1))]");
        System.out.println(input);
        System.out.println(ExtractParentheses.countParentheses("[a,(2-(1/1))]", '('));
        System.out.println(ExtractParentheses.countParentheses("[a,(2-(1/1))]", ')'));
    }
}
