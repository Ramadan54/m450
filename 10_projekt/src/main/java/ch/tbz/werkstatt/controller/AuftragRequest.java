package ch.tbz.werkstatt.controller;

import ch.tbz.werkstatt.model.Auftrag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record AuftragRequest(
        @NotBlank String kundenname,
        @NotBlank String fahrzeug,
        @NotBlank String teilenummer,
        @NotNull @PositiveOrZero BigDecimal arbeitsstunden,
        @NotNull @PositiveOrZero BigDecimal teilekosten,
        boolean stammkunde
) {

    public Auftrag toAuftrag() {
        return new Auftrag(kundenname, fahrzeug, teilenummer, arbeitsstunden, teilekosten, stammkunde);
    }
}
