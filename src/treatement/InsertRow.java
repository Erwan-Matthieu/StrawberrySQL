package treatement;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import table.TableDefinition;
import valueType.F;
import valueType.I;

public class InsertRow {
    private static String tableName;

    public InsertRow(String tableName) {
        InsertRow.tableName = tableName;
    }

    private static boolean insertValue(String... value){
        boolean check = false;

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            
            String relativePath = "src\\output\\data" + File.separator + tableName + ".json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            ObjectNode firstRow = (ObjectNode) myValArrayNode.get(0);

            ObjectNode newRow = objectMapper.createObjectNode();

            if (value.length == firstRow.size()) {
                int columnIndex = 0;

                Iterator<String> fieldNAmes = firstRow.fieldNames();

                while (fieldNAmes.hasNext()) {
                    String fieldName = fieldNAmes.next();

                    String valueType = firstRow.get(fieldName).asText();

                    switch (valueType) {
                        case "increment":
                            newRow.put(fieldName, F.getNext(myValArrayNode, fieldName));
                            columnIndex++;
                            break;
                    
                        case "integer":
                            I i = new I(value[columnIndex]);
                            newRow.put(fieldName, i.checkValue());
                            columnIndex++;
                            break;

                        default:
                            newRow.put(fieldName, value[columnIndex]);
                            columnIndex++;
                            break;
                    }
                }
            }

            myValArrayNode.add(newRow);

            objectMapper.writeValue(filePath, root);

            check = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return check;
    }

    public static boolean insertRow(String value) {
        boolean check = false;

        String[] values = value.substring(1, value.length() - 1).split(",");

        new TableDefinition(tableName);

        if (TableDefinition.getColumnSize() == values.length) {
            if (insertValue(values)) {
                check = true;
            }
        } else if (TableDefinition.getColumnSize() > values.length) {
            System.out.println("Le nombre de valeurs à insérer dépasse ce que peut acceuillir la table");
        } else {
            System.out.println("Le nombre de valeurs à insérer est en-dessous de ce que peut acceuillir la table");
        }

        return check;
    }

    public static void insertValueInTempTable(List<String> values){
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            
            String relativePath = "src\\output\\data" + File.separator + tableName + ".json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get(tableName);

            ObjectNode firstRow = (ObjectNode) myValArrayNode.get(0);
            
            ObjectNode newRow = objectMapper.createObjectNode();

            if (values.size() == firstRow.size()) {
                int columnIndex = 0;

                Iterator<String> fieldNAmes = firstRow.fieldNames();

                while (fieldNAmes.hasNext()) {
                    String fieldName = fieldNAmes.next();

                    newRow.put(fieldName, values.get(columnIndex));

                    columnIndex++;
                }
            }

            myValArrayNode.add(newRow);

            objectMapper.writeValue(filePath, root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}