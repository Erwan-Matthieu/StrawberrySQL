package test;

public class ArrayToStringExample {
    public static void main(String[] args) {
        String[] values = {"value1", "value2", "value3"};

        // Check if the array is empty
        if (values.length == 0) {
            System.out.println("Array is empty.");
            return;
        }

        StringBuilder result = new StringBuilder("(");

        for (int i = 0; i < values.length; i++) {
            result.append(values[i]);

            // Add a comma and space if not the last element
            if (i < values.length - 1) {
                result.append(", ");
            }
        }

        result.append(")");

        String formattedString = result.toString();

        System.out.println(formattedString);
    }
}
