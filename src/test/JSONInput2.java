package test;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSONInput2 {
    public static void main(String[] args) {
        String filePath = "src\\output\\data\\test2.json";

        String absolutePath = System.getProperty("user.dir") + File.separator + filePath;

        ObjectMapper objectMapper = new ObjectMapper();
        
        ObjectNode root = objectMapper.createObjectNode();

        ArrayNode myValArrayNode = objectMapper.createArrayNode();

        ObjectNode jsonObjectNode = objectMapper.createObjectNode();
        jsonObjectNode.put("kjfhvb", "dljfkvnb");
        jsonObjectNode.put("jkzhdnf", 3584586);

        myValArrayNode.add(jsonObjectNode);

        root.set("myTable", myValArrayNode);

        try {
            objectMapper.writeValue(new File(absolutePath), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
