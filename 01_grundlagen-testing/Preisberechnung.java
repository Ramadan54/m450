public class Preisberechnung {

    // Original-Code aus der Aufgabe (enthaelt den Fehler aus dem Bonus)
    static double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount;
        double result;

        if (extras >= 3)
            addon_discount = 10;
        else if (extras >= 5)
            addon_discount = 15;
        else
            addon_discount = 0;

        if (discount > addon_discount)
            addon_discount = discount;

        result = baseprice / 100.0 * (100 - discount) + specialprice
                + extraprice / 100.0 * (100 - addon_discount);

        return result;
    }

    // Hilfsmethode: vergleicht Ist- und Soll-Wert, gibt PASS oder FAIL aus
    static boolean check(String name, double ist, double soll) {
        boolean ok = Math.abs(ist - soll) < 0.001;
        System.out.println((ok ? "PASS" : "FAIL") + " | " + name
                + " | erwartet: " + soll + " | erhalten: " + ist);
        return ok;
    }

    // Testtreiber: ruft calculatePrice mit verschiedenen Werten auf und prueft die Resultate
    static boolean test_calculate_price() {
        boolean test_ok = true;

        // 1. Nur Grundpreis, keine Rabatte
        test_ok &= check("Nur Grundpreis", calculatePrice(20000, 0, 0, 0, 0), 20000);

        // 2. Grundpreis mit 10% Haendlerrabatt
        test_ok &= check("Haendlerrabatt 10%", calculatePrice(20000, 0, 0, 0, 10), 18000);

        // 3. 3 Extras -> 10% Rabatt auf Zubehoer
        test_ok &= check("3 Extras", calculatePrice(20000, 0, 1000, 3, 0), 20900);

        // 4. 5 Extras -> sollten 15% Rabatt auf Zubehoer sein
        test_ok &= check("5 Extras", calculatePrice(20000, 0, 1000, 5, 0), 20850);

        return test_ok;
    }

    public static void main(String[] args) {
        boolean alleOk = test_calculate_price();
        System.out.println("----------------------------------------");
        System.out.println(alleOk ? "Alle Tests bestanden." : "Mindestens ein Test fehlgeschlagen.");
    }
}