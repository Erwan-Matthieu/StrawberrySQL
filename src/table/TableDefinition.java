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

public class TableDefinition {
    private static String tableName;

    public TableDefinition(String tableName) {
        TableDefinition.tableName = tableName;
    }

    public String getName() {
        return tableName;
    }

    public static String getColumn(String valueType) {
        String column = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            String relativePath = "src\\output\\data" + File.separator + tableName +".json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            ObjectNode firstRow = (ObjectNode) myValArrayNode.get(0);

            Iterator<String> fieldNames= firstRow.fieldNames();

            List<String> matchingList = new ArrayList<>();

            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                String value = firstRow.get(fieldName).asText();

                if (value.equals(valueType)) {
                    matchingList.add(fieldName);
                }
            }

            StringBuilder result = new StringBuilder();

            int i = 0;

            for (String fieldName : matchingList) {
                result.append(fieldName);

                i++;

                if (i < matchingList.size()) {
                    result.append(",");
                }
            }

            if (matchingList.size() < 2) {
                switch (valueType) {
                    case "increment":
                        column = "#SQL => Dans la colonne de " + matchingList.get(0) + " qui est de type " + valueType + ", insérez juste 'null' comme valeur";
                        break;
                
                    case "string":
                        column = "#SQL => Dans la colonne de " + matchingList.get(0) + " qui est de type " + valueType + ", insérez une chaine de caractères";
                        break;
                
                    case "integer":
                        column = "#SQL => Dans la colonne de " + matchingList.get(0) + " qui est de type " + valueType + ", insérez une chaine numérique";
                        break;
                    default:
                        break;
                }
            } else {
                switch (valueType) {
                    case "increment":
                        column = "#SQL => Dans les colonnes " + result.toString() + " qui sont de type " + valueType + ", insérez juste 'null' comme valeur";
                        break;
                
                    case "string":
                        column = "#SQL => Dans les colonnes " + result.toString() + " qui sont de type " + valueType + ", insérez une chaine de caractères";
                        break;
                
                    case "integer":
                        column = "#SQL => Dans les colonnes " + result.toString() + " qui sont de type " + valueType + ", insérez une chaine numérique";
                        break;
                    default:
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return column;
    }

    public static String getColumnType() {
        String columnType = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            String relativePath = "src\\output\\data" + File.separator + tableName +".json";

            File filePath = new File(relativePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            ObjectNode firstRow = (ObjectNode) myValArrayNode.get(0);

            Iterator<String> fieldNames= firstRow.fieldNames();

            List<String> fieldNamesList = new ArrayList<>();

            while (fieldNames.hasNext()) {
                fieldNamesList.add(fieldNames.next());
            }

            StringBuilder result = new StringBuilder("(");

            int i = 0;

            for (String fieldName : fieldNamesList) {
                result.append(firstRow.get(fieldName).asText());

                i++;
                if (i < fieldNamesList.size()) {
                    result.append(",");
                }
            }

            result.append(")");

            columnType = result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return columnType;
    }

    public static int getColumnSize() {
        int size = 0;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            String relativePath = "src\\output\\data" + File.separator + tableName +".json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            ObjectNode firstRow = (ObjectNode) myValArrayNode.get(0);

            Iterator<String> fieldNames= firstRow.fieldNames();

            List<String> fieldNamesList = new ArrayList<>();
            
            while (fieldNames.hasNext()) {
                fieldNamesList.add(fieldNames.next());
            }

            size = fieldNamesList.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public static List<String> getHeaderList() {
        List<String> headerList = new ArrayList<>();
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            String relativePath = "src\\output\\data" + File.separator + tableName +".json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            ObjectNode firstRow = (ObjectNode) myValArrayNode.get(0);

            Iterator<String> fieldNames= firstRow.fieldNames();

            while (fieldNames.hasNext()) {
                headerList.add(fieldNames.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return headerList;
    }

    public static boolean getTableExist() {
        String relativePath = "src\\output\\data" + File.separator + tableName +".json";

        String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

        File filePath = new File(absolutePath);

        return filePath.exists();
    }

    public static String[] getStringArray(String value) {
        return value.substring(1, value.length() - 1).split(",");
    }

    public static List<Pair<String,String>> getColumnValueTypeList() {
        List<Pair<String,String>> valueTypeList = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            String relativePath = "src\\output\\data" + File.separator + tableName +".json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            ObjectNode firstRow = (ObjectNode) myValArrayNode.get(0);

            Iterator<String> fieldNames= firstRow.fieldNames();

            List<String> fieldNamesList = new ArrayList<>();

            while (fieldNames.hasNext()) {
                fieldNamesList.add(fieldNames.next());
            }

            for (String fieldName : fieldNamesList) {
                valueTypeList.add(new Pair<String,String>(fieldName, firstRow.get(fieldName).asText()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return valueTypeList;
    }

    public static boolean exist(String nameTable) {
        String relativePath = "src\\output\\data" + File.separator + nameTable + ".json";

        File filePath = new File(relativePath);

        return filePath.exists();
    }

    public static int getColumnIndex (String column){
        int index = 0;

        String relativePath = "src\\output\\data" + File.separator + tableName + ".json";

        File filePath = new File(relativePath);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            ObjectNode firstRow = (ObjectNode) myValArrayNode.get(0);

            Iterator<String> fieldNames = firstRow.fieldNames();

            int n = 1;

            while (fieldNames.hasNext()) {
                if (fieldNames.next().equals(column)) {
                    index = n;
                }
                n++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return index - 1;
    }
}
