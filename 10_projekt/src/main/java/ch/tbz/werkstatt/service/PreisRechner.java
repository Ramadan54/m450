package ch.tbz.werkstatt.service;

import java.math.BigDecimal;

public class PreisRechner {

    public static final BigDecimal STUNDENSATZ = new BigDecimal("120.00");
    public static final BigDecimal MENGENRABATT_GRENZE = new BigDecimal("1000.00");
    public static final BigDecimal MENGENRABATT_FAKTOR = new BigDecimal("0.95");

    public BigDecimal berechnePreis(BigDecimal arbeitsstunden, BigDecimal teilekosten, boolean stammkunde) {
        BigDecimal arbeitskosten = arbeitsstunden.multiply(STUNDENSATZ);
        BigDecimal zwischensumme = arbeitskosten.add(teilekosten);

        if (zwischensumme.compareTo(MENGENRABATT_GRENZE) >= 0) {
            return zwischensumme.multiply(MENGENRABATT_FAKTOR);
        }
        return zwischensumme;
    }
}
