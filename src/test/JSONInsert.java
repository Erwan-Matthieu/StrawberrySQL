package test;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSONInsert {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String relativePath = "src\\output\\data\\test.json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ArrayNode myValArrayNode = (ArrayNode) root.get("myTable");

            ObjectNode jsonObjectNode = objectMapper.createObjectNode();
            jsonObjectNode.put("pokjfdvcs", 3583528);

            myValArrayNode.add(jsonObjectNode);
            
            objectMapper.writeValue(filePath, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
