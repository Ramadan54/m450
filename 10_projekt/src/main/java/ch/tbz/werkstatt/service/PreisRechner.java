package ch.tbz.werkstatt.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PreisRechner {

    public static final BigDecimal STUNDENSATZ = new BigDecimal("120.00");
    public static final BigDecimal MENGENRABATT_GRENZE = new BigDecimal("1000.00");
    public static final BigDecimal MENGENRABATT_SATZ = new BigDecimal("0.05");
    public static final BigDecimal STAMMKUNDENRABATT_SATZ = new BigDecimal("0.10");

    public BigDecimal berechnePreis(BigDecimal arbeitsstunden, BigDecimal teilekosten, boolean stammkunde) {
        validiereEingaben(arbeitsstunden, teilekosten);

        BigDecimal zwischensumme = berechneZwischensumme(arbeitsstunden, teilekosten);
        BigDecimal rabattsatz = berechneRabattsatz(zwischensumme, stammkunde);

        return zwischensumme
                .multiply(BigDecimal.ONE.subtract(rabattsatz))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private void validiereEingaben(BigDecimal arbeitsstunden, BigDecimal teilekosten) {
        if (arbeitsstunden.signum() < 0) {
            throw new IllegalArgumentException("Arbeitsstunden dürfen nicht negativ sein");
        }
        if (teilekosten.signum() < 0) {
            throw new IllegalArgumentException("Teilekosten dürfen nicht negativ sein");
        }
    }

    private BigDecimal berechneZwischensumme(BigDecimal arbeitsstunden, BigDecimal teilekosten) {
        return arbeitsstunden.multiply(STUNDENSATZ).add(teilekosten);
    }

    private BigDecimal berechneRabattsatz(BigDecimal zwischensumme, boolean stammkunde) {
        BigDecimal rabattsatz = BigDecimal.ZERO;
        if (zwischensumme.compareTo(MENGENRABATT_GRENZE) >= 0) {
            rabattsatz = rabattsatz.add(MENGENRABATT_SATZ);
        }
        if (stammkunde) {
            rabattsatz = rabattsatz.add(STAMMKUNDENRABATT_SATZ);
        }
        return rabattsatz;
    }
}
