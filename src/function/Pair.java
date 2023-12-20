package function;

public class Pair<K,V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public static Pair<String,String> getPair(String condition) {
        String[] conditions = condition.substring(1, condition.length() - 1).split(",");

        if (conditions.length == 2) {
            return new Pair<String,String> (conditions[0],conditions[1]);
        } else {
            throw new IllegalArgumentException("La condition entrée est invalide");
        }
    }
}
