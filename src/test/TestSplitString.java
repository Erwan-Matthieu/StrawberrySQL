package test;

public class TestSplitString {
    public static void main(String[] args) {
        String input = "(null, sample1, sample2, 77, 32),(null, element1, element2, 33, 44),(null, info1, info2, 66, 22)";
        String[] sets = input.split("\\),");

        for (String set : sets) {
            if (!set.endsWith(")")) {
                set += ")";
            }
            System.out.println("Separated values with parentheses: " + set);
        }

        String[] values = input.split("\\),\\(");

        for (String set : values) {
            // Remove leading and trailing parentheses
            set = set.replaceAll("^\\(", "").replaceAll("\\)$", "");
            System.out.println("Separated values: " + set);
        }

        String input1 = "(null, sample1, sample2, 77, 32)";
        String[] sets1 = null;

        if (input1.contains("),")) {
            sets1 = input1.split("\\),");
            for (int i = 0; i < sets1.length; i++) {
                String set = sets1[i];
                if (!set.endsWith(")")) {
                    set += ")";
                }
                System.out.println("Separated values with parentheses: " + set);
            }
        } else {
            System.out.println("Input without splitting: " + input1);
        }


    }
}
