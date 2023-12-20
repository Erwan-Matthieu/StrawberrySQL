package valueType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class F {
    public F() {
    
    }

    public static int getNext (ArrayNode data, String fieldName) {
        int maxId = 0;

        for (JsonNode node : data) {
            int n = node.get(fieldName).asInt();

            if (n > maxId) {
                maxId = n;
            }
        }

        return maxId + 1;
    }
    
}
