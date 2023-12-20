package valueType;

/**
 * I
 */
public class I {
    private String i;

    public I(String value) {
        this.i = value;
    }

    private static boolean isValidateValue(int newvalue){
        boolean check = false;

        if (newvalue >= 0 && newvalue <= 1000000000) {
            check = true;
        }

        return check;
    }
    
    public int checkValue() {
        int n = 0;

        if ((i instanceof String)) {
            n = Integer.parseInt(i);

            if (isValidateValue(n)) {
                return n;
            } else {
                throw new IllegalArgumentException("La donnée ne correspond pas aux exigences du type de la variable");
            }
        } else {
            throw new IllegalArgumentException("La donnée ne correspond pas aux exigences du type de la variable");
        }
    }
}