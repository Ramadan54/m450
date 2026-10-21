package ch.tbz.werkstatt.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PreisRechnerTest {

    private final PreisRechner preisRechner = new PreisRechner();

    @Test
    void berechnePreis_ohneRabatt_ergibtStundenMalSatzPlusTeile() {
        BigDecimal preis = preisRechner.berechnePreis(new BigDecimal("2"), new BigDecimal("150.00"), false);

        // 2 h × 120 CHF + 150 CHF Teile = 390 CHF
        assertThat(preis).isEqualByComparingTo("390.00");
    }

    @Test
    void berechnePreis_ohneTeile_ergibtNurArbeitskosten() {
        BigDecimal preis = preisRechner.berechnePreis(new BigDecimal("3"), BigDecimal.ZERO, false);

        // 3 h × 120 CHF = 360 CHF
        assertThat(preis).isEqualByComparingTo("360.00");
    }
}
