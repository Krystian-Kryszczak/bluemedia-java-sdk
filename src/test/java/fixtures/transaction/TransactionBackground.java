package fixtures.transaction;

import krystian.kryszczak.bm.sdk.serializer.XmlSerializer;
import krystian.kryszczak.bm.sdk.transaction.request.TransactionBackgroundRequest;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import static fixtures.Fixtures.FIXTURES_FOLDER_PATH;

public abstract class TransactionBackground {
    public static @NotNull TransactionBackgroundRequest getTransactionBackground() {
        return TransactionBackgroundRequest.builder()
            .setGatewayUrl("https://pay-accept.bm.pl")
            .setTransaction(
                krystian.kryszczak.bm.sdk.transaction.TransactionBackground.builder()
                    .orderID("12345")
                    .amount("5.12")
                    .description("Test transaction 12345")
                    .gatewayID(21)
                    .currency("PLN")
                    .customerEmail("test@test.test")
                    .customerIP("127.0.0.1")
                    .title("Test")
                    .validityTime(LocalDateTime.now().plusHours(5))
                    .linkValidityTime(LocalDateTime.now().plusHours(5))
                    .build()
            )
            .build();
    }

    @SneakyThrows
    public static String getTransactionBackgroundResponse() {
        return Files.readString(Path.of(FIXTURES_FOLDER_PATH + "transaction/TransactionBackgroundResponse.xml"));
    }

    public static @NotNull Map<String, String> getTransactionBackgroundResponseData() {
        final var xmlSerializer = new XmlSerializer();
        return Objects.requireNonNull(xmlSerializer.toMap(getTransactionBackgroundResponse()));
    }

    @SneakyThrows
    public static String getPaywayFormResponse() {
        return Files.readString(Path.of(FIXTURES_FOLDER_PATH + "transaction/PaywayFormResponse.txt"));
    }
}
