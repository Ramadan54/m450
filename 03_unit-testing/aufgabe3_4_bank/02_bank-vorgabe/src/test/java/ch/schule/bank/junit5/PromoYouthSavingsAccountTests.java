package ch.schule.bank.junit5;

import ch.schule.PromoYouthSavingsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse PromoYouthSavingsAccount (Jugend-Sparkonto).
 * Besonderheit: Bei jeder Einzahlung gibt es 1% Bonus.
 *
 * @author Ramadan Asani
 * @version 1.0
 */
public class PromoYouthSavingsAccountTests {

	private PromoYouthSavingsAccount account;

	// Beträge in Millirappen: 1 Franken = 100'000 Millirappen
	private static final long HUNDRED_FRANCS = 10_000_000L;

	@BeforeEach
	void setUp() {
		account = new PromoYouthSavingsAccount("Y-4000");
	}

	@Test
	@DisplayName("Einzahlung von 100 ergibt 101 dank 1% Bonus")
	public void testDepositWithBonus() {
		account.deposit(1, HUNDRED_FRANCS);
		// 100 Franken + 1% Bonus = 101 Franken
		long expected = HUNDRED_FRANCS + (HUNDRED_FRANCS / 100);
		assertEquals(expected, account.getBalance());
	}

	@Test
	@DisplayName("Der Bonus beträgt genau 1% der Einzahlung")
	public void testBonusAmount() {
		long einzahlung = HUNDRED_FRANCS;
		account.deposit(1, einzahlung);
		long bonus = account.getBalance() - einzahlung;
		assertEquals(einzahlung / 100, bonus);
	}

	@Test
	@DisplayName("Als Sparkonto darf nicht mehr abgehoben werden als vorhanden")
	public void testWithdrawNotMoreThanBalance() {
		account.deposit(1, HUNDRED_FRANCS); // ergibt 101
		// Versuch, mehr abzuheben als vorhanden
		boolean result = account.withdraw(2, HUNDRED_FRANCS * 2);
		assertFalse(result);
	}
}