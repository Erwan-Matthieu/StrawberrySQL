package table;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import function.Pair;

public class Row {
    private static String tableName;

    public Row(String tableName){
        Row.tableName = tableName;
    }

    public static List<List<String>> getRow() {
        List<List<String>> rows = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            String relativePath = "src\\output\\data" + File.separator + tableName +".json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            for (int i = 1; i < getNumberofRows() + 1; i++) {
                ObjectNode currRow = (ObjectNode) myValArrayNode.get(i);
                List<String> row = new ArrayList<>();

                Iterator<String> fieldNames = currRow.fieldNames();

                while (fieldNames.hasNext()) {
                    row.add(currRow.get(fieldNames.next()).asText());
                }

                rows.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;
    }   
    
    public static int getNumberofRows() {
        int numberOfRows = 0;

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String relativePath = "src\\output\\data" + File.separator + tableName +".json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            numberOfRows = myValArrayNode.size() - 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numberOfRows;
    }

    public static List<List<String>> getRowWithSpecificColumns(String... columns) {
        List<List<String>> rows = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            String relativePath = "src\\output\\data" + File.separator + tableName +".json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            for (int i = 1; i < getNumberofRows() + 1; i++) {
                ObjectNode currRow = (ObjectNode) myValArrayNode.get(i);
                List<String> row = new ArrayList<>();

                for (String column : columns) {
                    row.add(currRow.get(column).asText());
                }

                rows.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static List<List<String>> getRowWithCondition(List<Pair<String,String>> conditions) {
        List<List<String>> rows = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            String relativePath = "src\\output\\data" + File.separator + tableName +".json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            List<Integer> rowConfirmed = new ArrayList<>();

            for (Pair<String,String> pairCondition : conditions) {
                for (int i = 1; i < getNumberofRows() + 1; i++) {
                    ObjectNode currRow = (ObjectNode) myValArrayNode.get(i);

                    if (currRow.get(pairCondition.getKey()).asText().equals(pairCondition.getValue())) {
                        rowConfirmed.add(i);
                    }
                }
            }

            ObjectNode firstRow = (ObjectNode) myValArrayNode.get(0);

            
            for (Integer integer : rowConfirmed) {
                List<String> row = new ArrayList<>();
                ObjectNode currRow = (ObjectNode) myValArrayNode.get(integer);

                Iterator<String> fieldNames= firstRow.fieldNames();

                while (fieldNames.hasNext()) {
                    row.add(currRow.get(fieldNames.next()).asText());
                }

                rows.add(row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;
    }

    public static List<List<String>> getRowWithSpecificColumnsWithCondition(List<Pair<String,String>> conditions, String... columns) {
        List<List<String>> rows = new ArrayList<>();
        
        for (List<String> column : getRowWithSpecificColumns(columns)) {
            List<String> row = new ArrayList<>();

            for (String list : column) {
                for (List<String> condition : getRowWithCondition(conditions)) {
                    if (condition.contains(list)) {
                        if (!row.contains(list)) {
                            row.add(list);
                        }
                    }
                }
            }

            if (!row.isEmpty() && row.size() == column.size()) {
                if (!rows.contains(row)) {
                    rows.add(row);
                }
            }
        }

        return rows;
    }
}
