package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.Map;

public class PayloadModifier {

    public static String updatePayload(String fileName, Map<String, String> testData) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        File file = new File("src/test/resources/payloads/" + fileName);
        JsonNode root = mapper.readTree(file);
        ObjectNode json = (ObjectNode) root;

        for (Map.Entry<String, String> entry : testData.entrySet()) {
            applyJsonPath(json, entry.getKey(), entry.getValue());
        }

        return mapper.writeValueAsString(json);
    }

    private static void applyJsonPath(JsonNode node, String path, String value) {
        String[] parts = path.split("\\.");
        JsonNode current = node;

        for (int i = 0; i < parts.length - 1; i++) {
            current = current.get(parts[i]);
        }

        ((ObjectNode) current).put(parts[parts.length - 1], value);
    }
}
