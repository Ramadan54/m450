package ch.tbz.werkstatt.service;

import java.math.BigDecimal;

public class PreisRechner {

    public static final BigDecimal STUNDENSATZ = new BigDecimal("120.00");
    public static final BigDecimal MENGENRABATT_GRENZE = new BigDecimal("1000.00");
    public static final BigDecimal MENGENRABATT_SATZ = new BigDecimal("0.05");
    public static final BigDecimal STAMMKUNDENRABATT_SATZ = new BigDecimal("0.10");

    public BigDecimal berechnePreis(BigDecimal arbeitsstunden, BigDecimal teilekosten, boolean stammkunde) {
        BigDecimal arbeitskosten = arbeitsstunden.multiply(STUNDENSATZ);
        BigDecimal zwischensumme = arbeitskosten.add(teilekosten);

        BigDecimal rabattsatz = BigDecimal.ZERO;
        if (zwischensumme.compareTo(MENGENRABATT_GRENZE) >= 0) {
            rabattsatz = rabattsatz.add(MENGENRABATT_SATZ);
        }
        if (stammkunde) {
            rabattsatz = rabattsatz.add(STAMMKUNDENRABATT_SATZ);
        }

        return zwischensumme.multiply(BigDecimal.ONE.subtract(rabattsatz));
    }
}
