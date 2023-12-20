package treatement;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CreateTable {
    private static String tableName;

    public CreateTable (String tableName) {
        CreateTable.tableName = tableName;
    }

    public static boolean createTable (String value, String valueType) {
        boolean check = false;
        ObjectMapper objectMapper = new ObjectMapper();
        
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String relativePath = "src\\output\\data" + File.separator + tableName + ".json";

        String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

        File filePath = new File(absolutePath);

        ObjectNode root = objectMapper.createObjectNode();

        ArrayNode myValArrayNode = objectMapper.createArrayNode();

        ObjectNode jsonObjectNode = objectMapper.createObjectNode();

        jsonObjectNode.put(value, valueType);

        myValArrayNode.add(jsonObjectNode);

        root.set(tableName, myValArrayNode);
        try {
            objectMapper.writeValue(filePath, root);
            
            check = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return check;
    }

    public static boolean insertColumnValueType (String value, String valueType) {
        boolean check = false;

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            
            String relativePath = "src\\output\\data" + File.separator + tableName + ".json";

            File filePath = new File(relativePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ObjectNode targetRow = (ObjectNode) root.get(tableName).get(0);

            targetRow.put(value, valueType);

            objectMapper.writeValue(filePath, root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return check;
    }

}
