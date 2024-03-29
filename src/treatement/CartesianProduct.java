package treatement;

import java.util.ArrayList;
import java.util.List;

import table.Row;
import table.TableDefinition;

public class CartesianProduct {
    private static String tableNameR;
    private static String tableNameS;

    public CartesianProduct(String tableNameR, String tableNameS) {
        CartesianProduct.tableNameR = tableNameR;
        CartesianProduct.tableNameS = tableNameS;
    }

    public static void createTempTable() {
        List<List<String>> table = new ArrayList<>();
        List<String> headerList = new ArrayList<>();

        new Row(tableNameR);
        new TableDefinition(tableNameR);
        
        List<List<String>> rowR = Row.getRow();

        for (String header : TableDefinition.getHeaderList()) {
            headerList.add(header);
        }

        new Row(tableNameS);
        new TableDefinition(tableNameS);

        List<List<String>> rowS = Row.getRow();

        for (String header : TableDefinition.getHeaderList()) {
            headerList.add(header);
        }

        for (List<String> rowR1 : rowR) {
            for (int i = 0; i < rowS.size(); i++) {
                List<String> row = new ArrayList<>();
                for (String cell : rowR1) {
                    row.add(cell);
                }

                for (String cell : rowS.get(i)) {
                    row.add(cell);
                }
                table.add(row);
            }
        }

        
    }

    public static void getTable(){
        List<List<String>> table = new ArrayList<>();
        List<String> headerList = new ArrayList<>();

        new Row(tableNameR);
        new TableDefinition(tableNameR);
        
        List<List<String>> rowR = Row.getRow();

        for (String header : TableDefinition.getHeaderList()) {
            headerList.add(header);
        }

        new Row(tableNameS);
        new TableDefinition(tableNameS);

        List<List<String>> rowS = Row.getRow();

        for (String header : TableDefinition.getHeaderList()) {
            headerList.add(header);
        }

        for (List<String> rowR1 : rowR) {
            for (int i = 0; i < rowS.size(); i++) {
                List<String> row = new ArrayList<>();
                for (String cell : rowR1) {
                    row.add(cell);
                }

                for (String cell : rowS.get(i)) {
                    row.add(cell);
                }
                table.add(row);
            }
        }

        System.out.println(TableGenerator.generateTable(headerList, table));

        if (table.size() < 2) {
            System.out.println(table.size() + " élément sélectionné");
        } else {
            System.out.println(table.size() + " éléments sélectionnés");
        }

    }

    public static String createTempCartesian() {
        List<List<String>> table = new ArrayList<>();
        List<String> headerList = new ArrayList<>();
        List<String> headerCartesian = new ArrayList<>();

        new TableDefinition(tableNameR);
        new Row(tableNameR);

        for (String header : TableDefinition.getHeaderList()) {
            headerList.add(header);
        }

        List<List<String>> rowR = Row.getRow();

        new TableDefinition(tableNameS);
        new Row(tableNameS);

        for (String header : TableDefinition.getHeaderList()) {
            if (headerList.contains(header)) {
                headerCartesian.add(header);
            }
            headerList.add(header);
        }

        List<List<String>> rowS = Row.getRow();

        for (List<String> rowR1 : rowR) {
            for (int i = 0; i < rowS.size(); i++) {
                List<String> row = new ArrayList<>();
                for (String cell : rowR1) {
                    row.add(cell);
                }

                for (String cell : rowS.get(i)) {
                    row.add(cell);
                }
                table.add(row);
            }
        }

        CreateTable createTable = new CreateTable(headerList,table);
        
        createTable.createTempTable();

        String tempName = createTable.getTempName();

        new InsertRow(tempName);

        for (List<String> row : table) {
            InsertRow.insertValueInTempTable(row);
        }

        return tempName;
    }
}
