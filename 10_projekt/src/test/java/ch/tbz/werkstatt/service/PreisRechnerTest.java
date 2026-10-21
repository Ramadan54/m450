package ch.tbz.werkstatt.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PreisRechnerTest {

    private final PreisRechner preisRechner = new PreisRechner();

    // ---------- Zyklus 1: Grundpreis ----------

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

    // ---------- Zyklus 2: Mengenrabatt ab 1000 CHF ----------

    @Test
    void berechnePreis_knappUnter1000_keinMengenrabatt() {
        BigDecimal preis = preisRechner.berechnePreis(new BigDecimal("5"), new BigDecimal("399.99"), false);

        // 5 h × 120 CHF + 399.99 CHF = 999.99 CHF → kein Rabatt
        assertThat(preis).isEqualByComparingTo("999.99");
    }

    @Test
    void berechnePreis_genau1000_erhaeltMengenrabatt() {
        BigDecimal preis = preisRechner.berechnePreis(new BigDecimal("5"), new BigDecimal("400.00"), false);

        // 5 h × 120 CHF + 400 CHF = 1000 CHF → 5 % Rabatt = 950 CHF
        assertThat(preis).isEqualByComparingTo("950.00");
    }

    @Test
    void berechnePreis_ueber1000_erhaeltMengenrabatt() {
        BigDecimal preis = preisRechner.berechnePreis(new BigDecimal("10"), new BigDecimal("300.00"), false);

        // 10 h × 120 CHF + 300 CHF = 1500 CHF → 5 % Rabatt = 1425 CHF
        assertThat(preis).isEqualByComparingTo("1425.00");
    }
}
