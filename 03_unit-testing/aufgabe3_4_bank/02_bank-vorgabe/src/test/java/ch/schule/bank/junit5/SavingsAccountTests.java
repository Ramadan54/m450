package ch.schule.bank.junit5;

import ch.schule.SavingsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse SavingsAccount (Sparkonto).
 * Besonderheit: Es kann nicht mehr abgehoben werden, als vorhanden ist.
 *
 * @author Ramadan Asani
 * @version 1.0
 */
public class SavingsAccountTests {

	private SavingsAccount account;

	// Beträge in Millirappen: 1 Franken = 100'000 Millirappen
	private static final long HUNDRED_FRANCS = 10_000_000L;
	private static final long FIFTY_FRANCS = 5_000_000L;

	@BeforeEach
	void setUp() {
		account = new SavingsAccount("S-2000");
	}

	@Test
	@DisplayName("Abheben innerhalb des Guthabens funktioniert")
	public void testWithdrawWithinBalance() {
		account.deposit(1, HUNDRED_FRANCS);
		boolean result = account.withdraw(2, FIFTY_FRANCS);
		assertTrue(result);
		assertEquals(FIFTY_FRANCS, account.getBalance());
	}

	@Test
	@DisplayName("Abheben von mehr als vorhanden wird abgelehnt")
	public void testWithdrawMoreThanBalance() {
		account.deposit(1, FIFTY_FRANCS);
		// Versuch, 100 abzuheben, obwohl nur 50 da sind
		boolean result = account.withdraw(2, HUNDRED_FRANCS);
		assertFalse(result);
		// Saldo muss unverändert bei 50 bleiben
		assertEquals(FIFTY_FRANCS, account.getBalance());
	}

	@Test
	@DisplayName("Abheben des gesamten Guthabens funktioniert (Grenzfall)")
	public void testWithdrawExactBalance() {
		account.deposit(1, HUNDRED_FRANCS);
		boolean result = account.withdraw(2, HUNDRED_FRANCS);
		assertTrue(result);
		assertEquals(0, account.getBalance());
	}
}