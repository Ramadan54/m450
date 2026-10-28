package ch.tbz.werkstatt.client;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SimulierterTeileLieferantClientTest {

    private final SimulierterTeileLieferantClient client = new SimulierterTeileLieferantClient();

    @ParameterizedTest
    @ValueSource(strings = {"TURBO-001", "AUSPUFF-002", "BREMSE-003", "FAHRWERK-004"})
    void istTeilVerfuegbar_bekanntesTeil_gibtTrue(String teilenummer) {
        assertThat(client.istTeilVerfuegbar(teilenummer)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"UNBEKANNT-999", "", "turbo-001"})
    void istTeilVerfuegbar_unbekanntesTeil_gibtFalse(String teilenummer) {
        // "turbo-001" klein geschrieben: Teilenummern sind case-sensitive
        assertThat(client.istTeilVerfuegbar(teilenummer)).isFalse();
    }
}
