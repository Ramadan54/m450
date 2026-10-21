package ch.tbz.werkstatt.service;

import java.math.BigDecimal;

public class PreisRechner {

    public static final BigDecimal STUNDENSATZ = new BigDecimal("120.00");

    public BigDecimal berechnePreis(BigDecimal arbeitsstunden, BigDecimal teilekosten, boolean stammkunde) {
        return BigDecimal.ZERO;
    }
}
