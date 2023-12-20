package table;

import java.util.ArrayList;
import java.util.List;

public class ColumnDefinition {
    public static List<String> getValueTypeList() {
        List<String> valueTypeList = new ArrayList<>();
        valueTypeList.add("increment");
        valueTypeList.add("string");
        valueTypeList.add("integer");

        return valueTypeList;
    }
}
