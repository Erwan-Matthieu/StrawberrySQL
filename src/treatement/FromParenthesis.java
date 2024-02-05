package treatement;

public class FromParenthesis {

    public static String cartesianTable(String[] tables, String condition) {
        new CartesianProduct(tables[0], tables[1]);

        return CartesianProduct.createTempCartesian();
    }

    public static String joinTable(String[] tables, String condition) {
        new NaturalJoin(tables[0], tables[1]);

        return NaturalJoin.createTempJoin();
    }
}
