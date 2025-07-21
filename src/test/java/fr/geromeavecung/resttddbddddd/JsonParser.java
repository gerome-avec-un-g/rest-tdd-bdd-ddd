package fr.geromeavecung.resttddbddddd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonParser {

    private static final String JSON = """
            {
              "userId": 1,
              "id": 2,
              "title": "delectus aut autem",
              "subtitle": "",
              "completed": false,
              "version": null
            }""";

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map<String, Object> fields = objectMapper.readValue(JSON, new TypeReference<>() {
        });
        printGherkinVertical(fields);
        printGherkinHorizontal(fields);
        printGherkinGlue(fields);
        printRecordClass(fields);
        printNewRecord(fields);
    }

    private static void printGherkinVertical(Map<String, Object> fields) {
        System.out.println();
        fields.forEach((k, v) -> System.out.println("|" + gherkinFieldName(k) + "|" + gherkinValue(v) + "|"));
    }

    private static void printGherkinHorizontal(Map<String, Object> fields) {
        System.out.println();
        System.out.println("|" + fields.keySet().stream().map(JsonParser::gherkinFieldName).collect(Collectors.joining("|")) + "|");
        System.out.println("|" + fields.values().stream().map(JsonParser::gherkinValue).collect(Collectors.joining("|")) + "|");
    }

    private static void printGherkinGlue(Map<String, Object> fields) {
        System.out.println();
        fields.keySet().forEach(key -> System.out.println("row.get(\"" + gherkinFieldName(key) + "\");"));
    }

    private static void printRecordClass(Map<String, Object> fields) {
        System.out.println();
        System.out.print("public record XXX(String ");
        System.out.print(String.join(", String ", fields.keySet()));
        System.out.print(") { }");
    }

    private static void printNewRecord(Map<String, Object> fields) {
        System.out.println();
        System.out.print("new XXX(");
        System.out.print(fields.values().stream().map(JsonParser::javaValue).collect(Collectors.joining(", ")));
        System.out.print(");");
    }

    private static String gherkinFieldName(String fieldName) {
        return String.join(" ", Arrays.asList(fieldName.split("(?=\\p{Upper})"))).toLowerCase();
    }

    private static String gherkinValue(Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof String valueAsString && valueAsString.isBlank()) {
                return "[blank]";
            }

        return value.toString();
    }

    private static String javaValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String valueAsString) {
                return "\"" + valueAsString + "\"";
        }
        return value.toString();
    }

}
