package table;

public class Rows<T> {
    private T[] values;

    @SafeVarargs
    public Rows(T... values) {
        this.values = values;
    }

    public void printValues() {
        for (T value : values) {
            System.out.println(value);
        }
    }
}
