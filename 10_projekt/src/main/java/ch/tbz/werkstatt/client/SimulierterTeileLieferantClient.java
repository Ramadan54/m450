package ch.tbz.werkstatt.client;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SimulierterTeileLieferantClient implements TeileLieferantClient {

    // Simuliert den Lagerbestand des externen Lieferanten
    private static final Set<String> VERFUEGBARE_TEILE = Set.of(
            "TURBO-001",
            "AUSPUFF-002",
            "BREMSE-003",
            "FAHRWERK-004"
    );

    @Override
    public boolean istTeilVerfuegbar(String teilenummer) {
        return VERFUEGBARE_TEILE.contains(teilenummer);
    }
}
