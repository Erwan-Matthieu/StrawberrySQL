package treatement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CreateTable {
    private static String tableName;
    private String tempName;
    private List<String> headerList;

    public CreateTable (String tableName) {
        CreateTable.tableName = tableName;
    }

    public CreateTable(List<String> headerList,List<List<String>> table) {
        this.tempName = UUID.randomUUID().toString();
        this.headerList = headerList;
    }
    
    public String getTempName() {
        return tempName;
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

    public static boolean insertColumnValueTypeInTempTable(String value, String valueType) {
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

    public void createTempTable() {
        ObjectMapper objectMapper = new ObjectMapper();
        
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String relativePath = "src\\output\\data" + File.separator + tempName + ".json";

        String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

        File filePath = new File(absolutePath);

        ObjectNode root = objectMapper.createObjectNode();

        ArrayNode myValArrayNode = objectMapper.createArrayNode();

        ObjectNode jsonObjectNode = objectMapper.createObjectNode();

        jsonObjectNode.put(headerList.get(0), "string");

        myValArrayNode.add(jsonObjectNode);

        root.set(tempName, myValArrayNode);
        try {
            objectMapper.writeValue(filePath, root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new CreateTable(tempName);

        for (int i = 1; i < headerList.size(); i++) {
            CreateTable.insertColumnValueTypeInTempTable(headerList.get(i), "string");
        }
    }

    public void suppressTempTable() {
        String relativePath = "src\\output\\data" + File.separator + tableName + ".json";

        String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

        Path path = Paths.get(absolutePath);

        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
