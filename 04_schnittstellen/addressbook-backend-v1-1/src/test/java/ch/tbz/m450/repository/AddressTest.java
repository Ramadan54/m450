package ch.tbz.m450.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse Address.
 * Address nutzt Lombok (@Getter, @Setter, @AllArgsConstructor, @NoArgsConstructor).
 *
 * @author Ramadan Asani
 * @version 1.0
 */
class AddressTest {

    private Address address;
    private Date registrationDate;

    @BeforeEach
    void setUp() {
        registrationDate = new Date();
        // Konstruktor: id, firstname, lastname, phonenumber, registrationDate
        address = new Address(1, "Max", "Muster", "0791234567", registrationDate);
    }

    @Test
    @DisplayName("Adresse gibt die im Konstruktor gesetzten Werte zurück")
    void testAddressValues() {
        assertEquals(1, address.getId());
        assertEquals("Max", address.getFirstname());
        assertEquals("Muster", address.getLastname());
        assertEquals("0791234567", address.getPhonenumber());
        assertEquals(registrationDate, address.getRegistrationDate());
    }

    @Test
    @DisplayName("Setter verändern die Werte korrekt")
    void testSetters() {
        address.setFirstname("Anna");
        address.setLastname("Beispiel");
        assertEquals("Anna", address.getFirstname());
        assertEquals("Beispiel", address.getLastname());
    }

    @Test
    @DisplayName("Leerer Konstruktor erzeugt ein Objekt, Werte lassen sich setzen")
    void testNoArgsConstructor() {
        Address leer = new Address();
        leer.setId(2);
        leer.setFirstname("Lea");
        assertEquals(2, leer.getId());
        assertEquals("Lea", leer.getFirstname());
    }
}