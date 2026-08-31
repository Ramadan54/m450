package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse AddressComparator.
 * Der Comparator sortiert Adressen nach dem Nachnamen.
 *
 * @author Ramadan Asani
 * @version 1.0
 */
class AddressComparatorTest {

    private AddressComparator comparator;
    private Address anderson;
    private Address zimmermann;

    @BeforeEach
    void setUp() {
        comparator = new AddressComparator();
        anderson = new Address(1, "Max", "Anderson", "079", new Date());
        zimmermann = new Address(2, "Anna", "Zimmermann", "078", new Date());
    }

    @Test
    @DisplayName("Nachname früher im Alphabet ergibt negativen Wert")
    void testCompareEarlierFirst() {
        // Anderson kommt vor Zimmermann -> negativ
        assertTrue(comparator.compare(anderson, zimmermann) < 0);
    }

    @Test
    @DisplayName("Nachname später im Alphabet ergibt positiven Wert")
    void testCompareLaterFirst() {
        // Zimmermann kommt nach Anderson -> positiv
        assertTrue(comparator.compare(zimmermann, anderson) > 0);
    }

    @Test
    @DisplayName("Gleicher Nach- und Vorname ergibt 0")
    void testCompareEqual() {
        // Jetzt müssen Nach- UND Vorname gleich sein, damit 0 herauskommt
        Address anderson2 = new Address(3, "Max", "Anderson", "077", new Date());
        assertEquals(0, comparator.compare(anderson, anderson2));
    }

    @Test
    @DisplayName("Eine Liste wird korrekt nach Nachnamen sortiert")
    void testSortList() {
        List<Address> list = new ArrayList<>(List.of(zimmermann, anderson));
        list.sort(comparator);
        // Nach dem Sortieren muss Anderson zuerst kommen
        assertEquals("Anderson", list.get(0).getLastname());
        assertEquals("Zimmermann", list.get(1).getLastname());
    }

    @Test
    @DisplayName("Bei gleichem Nachnamen wird nach Vornamen sortiert")
    void testCompareSameLastnameByFirstname() {
        Address andersonAnna = new Address(1, "Anna", "Anderson", "079", new Date());
        Address andersonMax = new Address(2, "Max", "Anderson", "078", new Date());
        // Anna kommt vor Max -> negativ
        assertTrue(comparator.compare(andersonAnna, andersonMax) < 0);
    }

    @Test
    @DisplayName("Liste mit gleichem Nachnamen wird nach Vornamen sortiert")
    void testSortByFirstnameWhenLastnameEqual() {
        Address andersonMax = new Address(1, "Max", "Anderson", "079", new Date());
        Address andersonAnna = new Address(2, "Anna", "Anderson", "078", new Date());
        List<Address> list = new ArrayList<>(List.of(andersonMax, andersonAnna));
        list.sort(comparator);
        // Anna muss nach dem Sortieren zuerst kommen
        assertEquals("Anna", list.get(0).getFirstname());
        assertEquals("Max", list.get(1).getFirstname());
    }
}