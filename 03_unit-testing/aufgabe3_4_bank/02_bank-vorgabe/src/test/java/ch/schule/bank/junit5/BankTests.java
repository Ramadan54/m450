package ch.schule.bank.junit5;

import ch.schule.Bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse Bank.
 * Die Bank verwaltet die Konten und leitet Ein- und Auszahlungen weiter.
 *
 * @author Ramadan Asani
 * @version 1.0
 */
public class BankTests {

    private Bank bank;

    // Beträge in Millirappen: 1 Franken = 100'000 Millirappen
    private static final long HUNDRED_FRANCS = 10_000_000L;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    @DisplayName("Neu erstelltes Sparkonto hat Saldo 0")
    public void testCreateSavingsAccount() {
        String id = bank.createSavingsAccount();
        assertNotNull(id);
        assertEquals(0, bank.getBalance(id));
    }

    @Test
    @DisplayName("Lohnkonto mit positiver Kreditlimite wird abgelehnt")
    public void testCreateSalaryAccountInvalid() {
        // Kreditlimite muss negativ oder 0 sein; positiv ist ungültig
        String id = bank.createSalaryAccount(5000);
        assertNull(id);
    }

    @Test
    @DisplayName("Einzahlen über die Bank erhöht den Kontostand")
    public void testDeposit() {
        String id = bank.createSavingsAccount();
        boolean result = bank.deposit(id, 1, HUNDRED_FRANCS);
        assertTrue(result);
        assertEquals(HUNDRED_FRANCS, bank.getBalance(id));
    }

    @Test
    @DisplayName("Einzahlen auf ein nicht existierendes Konto schlägt fehl")
    public void testDepositUnknownAccount() {
        boolean result = bank.deposit("X-9999", 1, HUNDRED_FRANCS);
        assertFalse(result);
    }

    @Test
    @DisplayName("Abfrage des Saldos eines unbekannten Kontos ergibt 0")
    public void testBalanceUnknownAccount() {
        assertEquals(0, bank.getBalance("X-9999"));
    }
}