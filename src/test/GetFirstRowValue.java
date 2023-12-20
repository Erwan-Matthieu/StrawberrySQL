package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class GetFirstRowValue {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            String relativePath = "src\\output\\data" + File.separator + "test.json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get("test");

            ObjectNode firstRow = (ObjectNode) myValArrayNode.get(0);

            Iterator<String> fieldNames= firstRow.fieldNames();

            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                System.out.println(firstRow.get(fieldName).asText());
                System.out.println(fieldName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
