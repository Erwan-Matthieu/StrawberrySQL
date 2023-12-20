package test;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSONInsertRow {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            String relativePath = "src\\output\\data\\test2.json";

            String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;

            File filePath = new File(absolutePath);

            ObjectNode root = objectMapper.readValue(filePath, ObjectNode.class);

            ObjectNode targetRow = (ObjectNode) root.get("myTable").get(0);

            targetRow.put("qsdfvbdsfvcecr", 312484318);

            objectMapper.writeValue(filePath, root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
