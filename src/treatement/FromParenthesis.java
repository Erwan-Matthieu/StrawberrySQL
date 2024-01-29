package treatement;

public class FromParenthesis {

    public static void cartesianTable(String[] tables, String condition) {
        
    }

    public static String joinTable(String[] tables, String condition) {
        new NaturalJoin(tables[0], tables[1]);

        return NaturalJoin.createTempJoin();
    }
}
