package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.SavingsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse Account.
 * Da Account abstrakt ist, wird über SavingsAccount getestet.
 *
 * @author Ramadan Asani
 * @version 1.0
 */
public class AccountTests {

    private Account account;

    // Beträge sind in Millirappen: 1 Franken = 100'000 Millirappen
    private static final long HUNDRED_FRANCS = 10_000_000L;
    private static final long FIFTY_FRANCS = 5_000_000L;

    @BeforeEach
    void setUp() {
        // SavingsAccount, weil Account selbst abstrakt ist
        account = new SavingsAccount("S-1000");
    }

    /**
     * Testet die Initialisierung eines Kontos.
     */
    @Test
    @DisplayName("Neues Konto hat die richtige ID und Saldo 0")
    public void testInit() {
        assertEquals("S-1000", account.getId());
        assertEquals(0, account.getBalance());
    }

    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    @DisplayName("Einzahlen erhöht den Kontostand")
    public void testDeposit() {
        boolean result = account.deposit(1, HUNDRED_FRANCS);
        assertTrue(result);
        assertEquals(HUNDRED_FRANCS, account.getBalance());
    }

    /**
     * Testet, dass ein negativer Betrag nicht eingezahlt werden kann.
     */
    @Test
    @DisplayName("Einzahlen eines negativen Betrags wird abgelehnt")
    public void testDepositNegative() {
        boolean result = account.deposit(1, -HUNDRED_FRANCS);
        assertFalse(result);
        assertEquals(0, account.getBalance());
    }

    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    @DisplayName("Abheben verringert den Kontostand")
    public void testWithdraw() {
        account.deposit(1, HUNDRED_FRANCS);
        boolean result = account.withdraw(2, FIFTY_FRANCS);
        assertTrue(result);
        assertEquals(FIFTY_FRANCS, account.getBalance());
    }

    /**
     * Testet die Referenz von SavingsAccount auf Account (Vererbung).
     */
    @Test
    @DisplayName("SavingsAccount ist ein Account")
    public void testReferences() {
        assertTrue(account instanceof Account);
        assertTrue(account instanceof SavingsAccount);
    }

    /**
     * Testet das canTransact-Flag: Eine Transaktion mit einem Datum
     * vor der letzten Buchung darf nicht erlaubt sein.
     */
    @Test
    @DisplayName("canTransact verhindert Transaktionen mit älterem Datum")
    public void testCanTransact() {
        // Vor der ersten Buchung ist alles erlaubt
        assertTrue(account.canTransact(5));

        account.deposit(10, HUNDRED_FRANCS);

        // Datum nach der letzten Buchung: erlaubt
        assertTrue(account.canTransact(11));
        // Datum vor der letzten Buchung: nicht erlaubt
        assertFalse(account.canTransact(9));
    }

    /**
     * Testet, dass print() ohne Fehler durchläuft.
     */
    @Test
    @DisplayName("print() läuft ohne Fehler")
    public void testPrint() {
        account.deposit(1, HUNDRED_FRANCS);
        assertDoesNotThrow(() -> account.print());
    }

    /**
     * Testet, dass der Monats-Kontoauszug ohne Fehler durchläuft.
     */
    @Test
    @DisplayName("print(year, month) läuft ohne Fehler")
    public void testMonthlyPrint() {
        account.deposit(1, HUNDRED_FRANCS);
        assertDoesNotThrow(() -> account.print(1970, 1));
    }
}