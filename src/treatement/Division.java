package treatement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import table.Row;
import table.TableDefinition;

public class Division {
    private static String tableNameR;
    private static String tableNameS;

    public Division(String tableNameR, String tableNameS) {
        Division.tableNameR = tableNameR;
        Division.tableNameS = tableNameS;
    }

    public void getTable() {
        List<List<String>> table = new ArrayList<>();
        List<String> headerList = new ArrayList<>();

        new Row(tableNameR);
        new TableDefinition(tableNameR);

        List<List<String>> rowR = Row.getRow();

        for (String header : TableDefinition.getHeaderList()) {
            headerList.add(header);
        }

        new Row(tableNameS);

        List<List<String>> rowS = Row.getRow();

        for (List<String> list : rowS) {
            for (String item : list) {
                for (List<String> list2 : rowR) {
                    if (list2.contains(item)) {
                        table.add(list2);
                    }
                }
            }
        }

        List<List<String>> temp = getUniqueCell(table, rowS,rowS.size());

        table = new ArrayList<>(temp);

        List<Integer> rowsToDelete = new ArrayList<>();

        int n = 0;

        for (List<String> list : table) {
            for (String element : list) {
                for (List<String> list2 : rowS) {
                    for (String item : list2) {
                        if (element.equals(item)) {
                            rowsToDelete.add(n);
                        }
                    }
                }
            }
            n++;
        }

        for (int rowToDelete : rowsToDelete) {
            table.remove(rowToDelete);
        }

        Set<List<String>> uniqueConditions = new LinkedHashSet<>(table);

        table = new ArrayList<>(uniqueConditions);

        headerList.remove(1);

        System.out.println(TableGenerator.generateTable(headerList, table));

        if (table.size() < 2) {
            System.out.println(table.size() + " élément sélectionné");
        } else {
            System.out.println(table.size() + " éléments sélectionnés");
        }
    }

    private static  List<List<String>> getUniqueCell(List<List<String>> lists,List<List<String>> conditions,int repetition) {
        Map<String,Integer> occurrences = new HashMap<>();
        List<List<String>> result = new ArrayList<>();

        for (List<String> list : lists) {
            for (String element : list) {
                occurrences.put(element, occurrences.getOrDefault(element, 0) + 1);
            }
        }

        for (Map.Entry<String,Integer> entry : occurrences.entrySet()) {
            if (entry.getValue() == repetition) {
                List<String> temp = new ArrayList<>();
                temp.add(entry.getKey());
                
                result.add(temp);
            }
        }

        return result;
    }
}