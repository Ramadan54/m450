package ch.schule.bank.junit5;

import ch.schule.SalaryAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse SalaryAccount (Lohnkonto).
 * Besonderheit: Das Saldo darf bis zur Kreditlimite (negative Zahl) ins Minus gehen.
 *
 * @author Ramadan Asani
 * @version 1.0
 */
public class SalaryAccountTests {

	private SalaryAccount account;

	// Beträge in Millirappen: 1 Franken = 100'000 Millirappen
	private static final long HUNDRED_FRANCS = 10_000_000L;
	private static final long TWO_HUNDRED_FRANCS = 20_000_000L;
	// Kreditlimite: -100 Franken (Konto darf bis -100 ins Minus)
	private static final long CREDIT_LIMIT = -10_000_000L;

	@BeforeEach
	void setUp() {
		account = new SalaryAccount("P-3000", CREDIT_LIMIT);
	}

	@Test
	@DisplayName("Abheben innerhalb des Guthabens funktioniert")
	public void testWithdrawWithinBalance() {
		account.deposit(1, HUNDRED_FRANCS);
		boolean result = account.withdraw(2, HUNDRED_FRANCS);
		assertTrue(result);
		assertEquals(0, account.getBalance());
	}

	@Test
	@DisplayName("Abheben bis zur Kreditlimite funktioniert (ins Minus erlaubt)")
	public void testWithdrawIntoCredit() {
		boolean result = account.withdraw(1, HUNDRED_FRANCS);
		assertTrue(result);
		assertEquals(-HUNDRED_FRANCS, account.getBalance());
	}

	@Test
	@DisplayName("Abheben über die Kreditlimite hinaus wird abgelehnt")
	public void testWithdrawBeyondCredit() {
		boolean result = account.withdraw(1, TWO_HUNDRED_FRANCS);
		assertFalse(result);
		assertEquals(0, account.getBalance());
	}
}