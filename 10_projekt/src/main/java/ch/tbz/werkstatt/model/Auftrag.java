package ch.tbz.werkstatt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Auftrag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String kundenname;

    @Column(nullable = false)
    private String fahrzeug;

    @Column(nullable = false)
    private String teilenummer;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal arbeitsstunden;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal teilekosten;

    private boolean stammkunde;

    @Column(precision = 10, scale = 2)
    private BigDecimal preis;

    public Auftrag() {
        // Wird von JPA benötigt
    }

    public Auftrag(String kundenname, String fahrzeug, String teilenummer,
                   BigDecimal arbeitsstunden, BigDecimal teilekosten, boolean stammkunde) {
        this.kundenname = kundenname;
        this.fahrzeug = fahrzeug;
        this.teilenummer = teilenummer;
        this.arbeitsstunden = arbeitsstunden;
        this.teilekosten = teilekosten;
        this.stammkunde = stammkunde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKundenname() {
        return kundenname;
    }

    public void setKundenname(String kundenname) {
        this.kundenname = kundenname;
    }

    public String getFahrzeug() {
        return fahrzeug;
    }

    public void setFahrzeug(String fahrzeug) {
        this.fahrzeug = fahrzeug;
    }

    public String getTeilenummer() {
        return teilenummer;
    }

    public void setTeilenummer(String teilenummer) {
        this.teilenummer = teilenummer;
    }

    public BigDecimal getArbeitsstunden() {
        return arbeitsstunden;
    }

    public void setArbeitsstunden(BigDecimal arbeitsstunden) {
        this.arbeitsstunden = arbeitsstunden;
    }

    public BigDecimal getTeilekosten() {
        return teilekosten;
    }

    public void setTeilekosten(BigDecimal teilekosten) {
        this.teilekosten = teilekosten;
    }

    public boolean isStammkunde() {
        return stammkunde;
    }

    public void setStammkunde(boolean stammkunde) {
        this.stammkunde = stammkunde;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }
}
