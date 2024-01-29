package treatement;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import function.Pair;
import table.Row;
import table.TableDefinition;

public class NaturalJoin {
    private static String tableNameR;
    private static String tableNameS;

    public NaturalJoin(String tableNameR, String tableNameS) {
        NaturalJoin.tableNameR = tableNameR;
        NaturalJoin.tableNameS = tableNameS;
    }

    // Natural join of two table when two columns name are the same
    @Deprecated
    public static void getTable() {
        List<List<String>> table = new ArrayList<>();
        List<String> headerList = new ArrayList<>();
        List<String> headerJoin = new ArrayList<>();

        new TableDefinition(tableNameR);

        // Get the header list of the first table
        for (String header : TableDefinition.getHeaderList()) {
            headerList.add(header);
        }

        new TableDefinition(tableNameS);

        // Get the header list of the second table and extract the same columns
        for (String header : TableDefinition.getHeaderList()) {
            if (headerList.contains(header)) {
                headerJoin.add(header);
            } else {
                headerList.add(header);
            }
        }

        // Get rows from the first table
        new Row(tableNameR);

        List<List<String>> rowR = Row.getRowWithSpecificColumns(headerJoin.toArray(new String[0])); 

        // Get rows from the second table
        new Row(tableNameS);

        List<List<String>> rowS = Row.getRowWithSpecificColumns(headerJoin.toArray(new String[0]));

        // Get the conditions
        List<List<String>> rows = new ArrayList<>();

        if (rowR.size() >= rowS.size()) {
            for (List<String> list : rowR) {
                if (rowS.contains(list)) {
                    rows.add(list);
                }
            }
        } else {
            for (List<String> list : rowS) {
                if (rowR.contains(list)) {
                    rows.add(list);
                }
            }
        }

        // Initialize the condition
        List<Pair<String,String>> conditions = new ArrayList<>();

        Set<List<String>> uniqueSet = new LinkedHashSet<>(rows);

        List<List<String>> newRow = new ArrayList<>(uniqueSet);

        int n = 0;

        // Link each condition to its column
        for (List<String> list : newRow) {
            for (String value : list) {
                conditions.add(new Pair<String,String>(headerJoin.get(n), value));
                n++;
            }
            n = 0;
        }

        // Get the first table with conditions
        new Row(tableNameR);

        List<List<String>> table1 = Row.getRowWithCondition(conditions);

        // Get the second table with conditions
        new Row(tableNameS);

        List<List<String>> table2 = Row.getRowWithCondition(conditions);

        List<List<String>> tableR = new ArrayList<>();

        // Get the rows from the first table with conditions
        for (List<String> list : table1) {
            for (List<String> list2 : newRow) {
                if (list.containsAll(list2)) {
                    tableR.add(list);
                }
            }
        }

        List<List<String>> tableS = new ArrayList<>();

        // Get the rows from the second table with conditions
        for (List<String> list : table2) {
            for (List<String> list2 : newRow) {
                if (list.containsAll(list2)) {
                    tableS.add(list);
                }
            }
        }

        // Set the first table and the second table
        Set<List<String>> uniqueTableR = new LinkedHashSet<>(tableR);
        Set<List<String>> uniqueTableS = new LinkedHashSet<>(tableS);

        List<List<String>> rowTableR = new ArrayList<>(uniqueTableR);
        List<List<String>> rowTableS = new ArrayList<>(uniqueTableS);

        // Create the new table
        for (List<String> list : rowTableR) {
            String last1 = list.get(list.size() - 1);
            String secondLast1 = list.get(list.size() - 2);

            for (List<String> list2 : rowTableS) {
                String first2 = list2.get(0);
                String second2 = list2.get(1);

                if (last1.equals(second2) && secondLast1.equals(first2)) {
                    List<String> combinedRow = new ArrayList<>(list);
                    combinedRow.addAll(list2.subList(2, list2.size()));
                    table.add(combinedRow);
                }
            }
        }

        System.out.println(TableGenerator.generateTable(headerList, table));

        if (table.size() < 2) {
            System.out.println(table.size() + " élément sélectionné");
        } else {
            System.out.println(table.size() + " éléments sélectionnés");
        }
    }

    public static void getTableFromColumns(String...columns) {
        List<List<String>> table = new ArrayList<>();
        List<String> headerList = new ArrayList<>();
        List<String> headerJoin = new ArrayList<>();

        for (String header : columns) {
            headerJoin.add(header);
        }

        new TableDefinition(tableNameR);

        for (String header : TableDefinition.getHeaderList()) {
            headerList.add(header);
        }

        new TableDefinition(tableNameS);

        for (String header : TableDefinition.getHeaderList()) {
            headerList.add(header);
        }

        Set<String> uniqueSet = new LinkedHashSet<>(headerList);

        headerList = new ArrayList<>(uniqueSet);

        new Row(tableNameR);

        List<List<String>> rowR = Row.getRowWithSpecificColumns(headerJoin.toArray(new String[0]));

        new Row(tableNameS);

        List<List<String>> rowS = Row.getRowWithSpecificColumns(headerJoin.toArray(new String[0]));

        List<List<String>> rows = new ArrayList<>();

        if (rowR.size() >= rowS.size()) {
            for (List<String> list : rowR) {
                if (rowS.contains(list)) {
                    rows.add(list);
                }
            }
        } else {
            for (List<String> list : rowS) {
                if (rowR.contains(list)) {
                    rows.add(list);
                }
            }
        }

        List<Pair<String,String>> conditions = new ArrayList<>();

        Set<List<String>> uniqueSetRows = new LinkedHashSet<>(rows);
        rows = new ArrayList<>(uniqueSetRows);

        int n = 0;

        for (List<String> list : rows) {
            for (String value : list) {
                conditions.add(new Pair<String,String>(headerJoin.get(n), value));
                n++;
            }
            n = 0;
        }

        new Row(tableNameR);

        List<List<String>> table1 = Row.getRowWithCondition(conditions);

        new Row(tableNameS);

        List<List<String>> table2 = Row.getRowWithCondition(conditions);

        List<List<String>> tableR = new ArrayList<>();

        for (List<String> list : table1) {
            for (List<String> list2 : rows) {
                if (list.containsAll(list2)) {
                    tableR.add(list);
                }
            }
        }

        List<List<String>> tableS = new ArrayList<>();

        for (List<String> list : table2) {
            for (List<String> list2 : rows) {
                if (list.containsAll(list2)) {
                    tableS.add(list);
                }
            }
        }

        uniqueSetRows = new LinkedHashSet<>(tableR);
        tableR = new ArrayList<>(uniqueSetRows);

        System.out.println(tableR);

        uniqueSetRows = new LinkedHashSet<>(tableS);
        tableS = new ArrayList<>(uniqueSetRows);

        System.out.println(tableS);

        Set<List<String>> uniqueRowTable = new LinkedHashSet<>(table);
        table = new ArrayList<>(uniqueRowTable);

        System.out.println(TableGenerator.generateTable(headerList, tableS));
    }

    public static void getNaturalJoin() {
        List<List<String>> table = new ArrayList<>();
        List<String> headerList = new ArrayList<>();
        List<String> headerJoin = new ArrayList<>();

        new TableDefinition(tableNameR);

        for (String header : TableDefinition.getHeaderList()) {
            headerList.add(header);
        }

        new TableDefinition(tableNameS);

        for (String header : TableDefinition.getHeaderList()) {
            if (headerList.contains(header)) {
                headerJoin.add(header);
            }
            headerList.add(header);
        }

        Set<String> uniqueSet = new LinkedHashSet<>(headerList);

        headerList = new ArrayList<>(uniqueSet);

        List<Integer> columnIndexR = new ArrayList<>();

        new TableDefinition(tableNameR);

        for (String header : headerJoin) {
            columnIndexR.add(TableDefinition.getColumnIndex(header));
        }

        List<Integer> columnIndexS = new ArrayList<>();

        new TableDefinition(tableNameS);

        for (String header : headerJoin) {
            columnIndexS.add(TableDefinition.getColumnIndex(header));
        }

        new Row(tableNameR);
        
        List<List<String>> rowR = Row.getRowWithSpecificColumns(headerJoin.toArray(new String[0]));

        new Row(tableNameS);

        List<List<String>> rowS = Row.getRowWithSpecificColumns(headerJoin.toArray(new String[0]));

        List<List<String>> rows = new ArrayList<>();

        if (rowR.size() >= rowS.size()) {
            for (List<String> list : rowR) {
                if (rowS.contains(list)) {
                    rows.add(list);
                }
            }
        } else {
            for (List<String> list : rowS) {
                if (rowR.contains(list)) {
                    rows.add(list);
                }
            }
        }

        List<Pair<String,String>> conditions = new ArrayList<>();

        Set<List<String>> uniqueConditions = new LinkedHashSet<>(rows);

        rows = new ArrayList<>(uniqueConditions);

        int n = 0;

        for (List<String> list : rows) {
            for (String value : list) {
                conditions.add(new Pair<String,String>(headerJoin.get(n), value));
                n++;
            }
            n = 0;
        }

        new Row(tableNameR);

        List<List<String>> table1 = Row.getRowWithCondition(conditions);

        new Row(tableNameS);

        List<List<String>> table2 = Row.getRowWithCondition(conditions);

        List<List<String>> tableR = new ArrayList<>();

        for (List<String> list : table1) {
            for (List<String> list2 : rows) {
                if (list.containsAll(list2)) {
                    tableR.add(list);
                }
            }
        }

        List<List<String>> tableS = new ArrayList<>();

        for (List<String> list : table2) {
            for (List<String> list2 : rows) {
                if (list.containsAll(list2)) {
                    tableS.add(list);
                }
            }
        }

        Set<List<String>> uniqueTableR = new LinkedHashSet<>(tableR);
        Set<List<String>> uniqueTableS = new LinkedHashSet<>(tableS);

        List<List<String>> rowTableR = new ArrayList<>(uniqueTableR);
        List<List<String>> rowTableS = new ArrayList<>(uniqueTableS);

        List<String> tableRJoinHeader = new ArrayList<>();
        List<String> tableSJoinHeader = new ArrayList<>();
        List<String> tableSTemp = new ArrayList<>();

        for (List<String> list : rowTableR) {
            for (int i = 0; i < columnIndexR.size(); i++) {
                tableRJoinHeader.add(list.get(columnIndexR.get(i)));
            }

            for (List<String> list2 : rowTableS) {
                for (int i = 0; i < columnIndexS.size(); i++) {
                    tableSJoinHeader.add(list2.get(columnIndexS.get(i)));
                }

                for (int i = 0; i < list2.size(); i++) {
                    if (!columnIndexS.contains(i)) {
                        tableSTemp.add(list2.get(i));
                    }
                }

                if (tableRJoinHeader.containsAll(tableSJoinHeader)) {
                    List<String> combinedRow = new ArrayList<>(list);
                    combinedRow.addAll(tableSTemp);
                    table.add(combinedRow);
                }

                tableSTemp.clear();
                tableSJoinHeader.clear();
            }
            tableRJoinHeader.clear();
        }

        System.out.println(TableGenerator.generateTable(headerList, table));

        if (table.size() < 2) {
            System.out.println(table.size() + " élément sélectionné");
        } else {
            System.out.println(table.size() + " éléments sélectionnés");
        }
    }

    public static String createTempJoin() {
        List<List<String>> table = new ArrayList<>();
        List<String> headerList = new ArrayList<>();
        List<String> headerJoin = new ArrayList<>();

        new TableDefinition(tableNameR);

        for (String header : TableDefinition.getHeaderList()) {
            headerList.add(header);
        }

        new TableDefinition(tableNameS);

        for (String header : TableDefinition.getHeaderList()) {
            if (headerList.contains(header)) {
                headerJoin.add(header);
            }
            headerList.add(header);
        }

        Set<String> uniqueSet = new LinkedHashSet<>(headerList);

        headerList = new ArrayList<>(uniqueSet);

        List<Integer> columnIndexR = new ArrayList<>();

        new TableDefinition(tableNameR);

        for (String header : headerJoin) {
            columnIndexR.add(TableDefinition.getColumnIndex(header));
        }

        List<Integer> columnIndexS = new ArrayList<>();

        new TableDefinition(tableNameS);

        for (String header : headerJoin) {
            columnIndexS.add(TableDefinition.getColumnIndex(header));
        }

        new Row(tableNameR);
        
        List<List<String>> rowR = Row.getRowWithSpecificColumns(headerJoin.toArray(new String[0]));

        new Row(tableNameS);

        List<List<String>> rowS = Row.getRowWithSpecificColumns(headerJoin.toArray(new String[0]));

        List<List<String>> rows = new ArrayList<>();

        if (rowR.size() >= rowS.size()) {
            for (List<String> list : rowR) {
                if (rowS.contains(list)) {
                    rows.add(list);
                }
            }
        } else {
            for (List<String> list : rowS) {
                if (rowR.contains(list)) {
                    rows.add(list);
                }
            }
        }

        List<Pair<String,String>> conditions = new ArrayList<>();

        Set<List<String>> uniqueConditions = new LinkedHashSet<>(rows);

        rows = new ArrayList<>(uniqueConditions);

        int n = 0;

        for (List<String> list : rows) {
            for (String value : list) {
                conditions.add(new Pair<String,String>(headerJoin.get(n), value));
                n++;
            }
            n = 0;
        }

        new Row(tableNameR);

        List<List<String>> table1 = Row.getRowWithCondition(conditions);

        new Row(tableNameS);

        List<List<String>> table2 = Row.getRowWithCondition(conditions);

        List<List<String>> tableR = new ArrayList<>();

        for (List<String> list : table1) {
            for (List<String> list2 : rows) {
                if (list.containsAll(list2)) {
                    tableR.add(list);
                }
            }
        }

        List<List<String>> tableS = new ArrayList<>();

        for (List<String> list : table2) {
            for (List<String> list2 : rows) {
                if (list.containsAll(list2)) {
                    tableS.add(list);
                }
            }
        }

        Set<List<String>> uniqueTableR = new LinkedHashSet<>(tableR);
        Set<List<String>> uniqueTableS = new LinkedHashSet<>(tableS);

        List<List<String>> rowTableR = new ArrayList<>(uniqueTableR);
        List<List<String>> rowTableS = new ArrayList<>(uniqueTableS);

        List<String> tableRJoinHeader = new ArrayList<>();
        List<String> tableSJoinHeader = new ArrayList<>();
        List<String> tableSTemp = new ArrayList<>();

        for (List<String> list : rowTableR) {
            for (int i = 0; i < columnIndexR.size(); i++) {
                tableRJoinHeader.add(list.get(columnIndexR.get(i)));
            }

            for (List<String> list2 : rowTableS) {
                for (int i = 0; i < columnIndexS.size(); i++) {
                    tableSJoinHeader.add(list2.get(columnIndexS.get(i)));
                }

                for (int i = 0; i < list2.size(); i++) {
                    if (!columnIndexS.contains(i)) {
                        tableSTemp.add(list2.get(i));
                    }
                }

                if (tableRJoinHeader.containsAll(tableSJoinHeader)) {
                    List<String> combinedRow = new ArrayList<>(list);
                    combinedRow.addAll(tableSTemp);
                    table.add(combinedRow);
                }

                tableSTemp.clear();
                tableSJoinHeader.clear();
            }
            tableRJoinHeader.clear();
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
