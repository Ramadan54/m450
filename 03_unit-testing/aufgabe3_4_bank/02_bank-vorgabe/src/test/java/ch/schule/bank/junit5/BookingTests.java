package ch.schule.bank.junit5;

import ch.schule.Booking;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse Booking (Buchung).
 * Eine Buchung speichert Datum und Betrag einer Transaktion.
 *
 * @author Ramadan Asani
 * @version 1.0
 */
public class BookingTests {

	@Test
	@DisplayName("Buchung speichert Datum und Betrag korrekt")
	public void testBookingValues() {
		Booking booking = new Booking(42, 5_000_000L);
		assertEquals(42, booking.getDate());
		assertEquals(5_000_000L, booking.getAmount());
	}

	@Test
	@DisplayName("Buchung kann einen negativen Betrag speichern (Abhebung)")
	public void testNegativeAmount() {
		// Abhebungen werden als negative Buchung gespeichert
		Booking booking = new Booking(10, -3_000_000L);
		assertEquals(-3_000_000L, booking.getAmount());
	}

	@Test
	@DisplayName("print() läuft ohne Fehler")
	public void testPrint() {
		Booking booking = new Booking(5, 1_000_000L);
		assertDoesNotThrow(() -> booking.print(0));
	}
}