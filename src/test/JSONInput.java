package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.*;

public class JSONInput {
    public static void main(String[] args) {
        List<Map<String, Object>> myTable = new ArrayList<>();

        Map<String, Object> entry1 = new HashMap<>();
        entry1.put("dsfg", "zlsjkdfh");
        entry1.put("jkshjfd", 13835);

        myTable.add(entry1);

        Map<String, Object> entry2 = new HashMap<>();
        entry2.put("dsfg", "hjbsgdf");
        entry2.put("jkshjfd", 13582351);

        myTable.add(entry2);

        ObjectMapper objectMapper = new ObjectMapper();

        String relativePath = "src\\output\\data\\test.json";

        String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;
        
        File jsonFile = new File(absolutePath);
        
        try {
            objectMapper.writeValue(jsonFile, myTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
