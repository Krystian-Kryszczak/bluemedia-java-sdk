package fixtures.itn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static fixtures.Fixtures.FIXTURES_FOLDER_PATH;

public abstract class Itn {
    @SneakyThrows
    public static String getItnInRequest() {
        return Base64.getEncoder().encodeToString(
            Files.readString(Path.of(FIXTURES_FOLDER_PATH + "itn/ItnInRequest.xml"))
                .getBytes()
        );
    }

    @SneakyThrows
    public static Map<String, String> getTransactionXml() {
        final var xml = new XmlMapper().readTree(Files.readString(Path.of(FIXTURES_FOLDER_PATH + "itn/ItnInRequest.xml")));
        final Map<String, String> result = new HashMap<>();
        final var fields = xml.findValue("transactions").findValue("transaction").fields();
        while (fields.hasNext()) {
            final var entry = fields.next();
            result.put(entry.getKey(), entry.getValue().asText());
        }
        return result;
    }

    @SneakyThrows
    public static String getItnInWrongHashRequest() {
        return Base64.getEncoder().encodeToString(
            Files.readString(Path.of(FIXTURES_FOLDER_PATH + "itn/ItnInWrongHashRequest.xml"))
                .getBytes()
        );
    }

    @SneakyThrows
    public static String getItnResponse() {
        return Files.readString(Path.of(FIXTURES_FOLDER_PATH + "itn/ItnResponse.xml"));
    }
}
