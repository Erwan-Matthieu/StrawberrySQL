package treatement;

import java.util.ArrayList;
import java.util.List;

import function.Pair;
import table.Row;
import table.TableDefinition;

public class Condition {
    private static String tableName;

    public Condition(String tableName) {
        Condition.tableName = tableName;
    }

    public static void createConditionTable(String originalTable, List<Pair<String,String>> conditions, String... columns) {
        new Row(originalTable);
        new TableDefinition(originalTable);
        new CreateTable(tableName);
        new InsertRow(tableName);

        List<List<String>> rows = new ArrayList<>();

        if (columns == null) {
            rows = Row.getRowWithCondition(conditions);
        } else if (!(conditions == null)) {
            rows = Row.getRowWithSpecificColumnsWithCondition(conditions, columns);
        } else if (conditions == null) {
            rows = Row.getRowWithSpecificColumns(columns);
        }

        List<Pair<String,String>> valueType = TableDefinition.getColumnValueTypeList();
        List<Pair<String,String>> valueTypeColumns = new ArrayList<>();

        int n = 0;

        try {
            for (Pair<String,String> pair : valueType) {
                for (String column : columns) {
                    if (pair.getKey().equals(column)) {
                        valueTypeColumns.add(new Pair<String,String>(pair.getKey(), pair.getValue()));
                    }
                }
            }

            for (Pair<String,String> pair : valueTypeColumns) {
                if (n == 0) {
                    CreateTable.createTable(pair.getKey(), pair.getValue());
                } else {
                    CreateTable.insertColumnValueType(pair.getKey(), pair.getValue());
                }
                n++;
            }

            StringBuilder result = new StringBuilder();

            n = 1;
            
            for (List<String> row : rows) {
                result.append("(");

                int i = 0;
                for (String thing : row) {
                    result.append(thing);
                    if (i < 1) {
                        result.append(",");
                    }

                    i++;
                }
                result.append(")");

                if (n < rows.size()) {
                    result.append(",");
                }

                n++;
            }

            String newRow = result.toString();

            int valueAffected = 0;

            if (newRow.contains("),")) {
                String[] splitValues = newRow.split("\\),");
                for (String value : splitValues) {
                    if (!value.endsWith(")")) {
                        value += ")";
                    }

                    if (InsertRow.insertRow(value)) {
                        valueAffected++;
                    }
                }

                System.out.println(valueAffected + " lignes de données ont été insérées avec succés");
            } else {
                if (InsertRow.insertRow(newRow)) {
                    System.out.println("Les données sont insérées avec succés");
                }
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Condition("test4");

        List<Pair<String,String>> conditions = new ArrayList<>();

        conditions.add(new Pair<String,String>("test2", "test1"));
        conditions.add(new Pair<String,String>("test2", "test5"));

        String[] columns = {"test2","test3"};

        createConditionTable("test", conditions, columns);
    }
}
