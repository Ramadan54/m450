# Modul 450 – Teststrategie

Bearbeitet von: Ramadan Asani

---

## Übung 1 – Testfälle aus Rabattregeln ableiten

Aus der Beschreibung ergeben sich vier Preisbereiche mit unterschiedlichem Rabatt:

- Kaufpreis unter 15'000 CHF → kein Rabatt (0%)
- Kaufpreis 15'000 bis 20'000 CHF → 5%
- Kaufpreis über 20'000 und unter 25'000 CHF → 7%
- Kaufpreis ab 25'000 CHF → 8,5%

### Abstrakte Testfälle

Hier wird mit logischen Operatoren statt konkreten Zahlen gearbeitet.

| ID | Bedingung (Kaufpreis) | Erwarteter Rabatt |
|----|-----------------------|-------------------|
| A1 | preis < 15'000 | 0% |
| A2 | 15'000 ≤ preis ≤ 20'000 | 5% |
| A3 | 20'000 < preis < 25'000 | 7% |
| A4 | preis ≥ 25'000 | 8,5% |

### Konkrete Testfälle

Hier werden echte Eingabewerte verwendet. Bewusst an den Grenzen gewählt, weil dort am ehesten Fehler auftreten (Grenzwertanalyse).

| ID | Kaufpreis (CHF) | Erwarteter Rabatt |
|----|-----------------|-------------------|
| K1 | 10'000 | 0% |
| K2 | 14'999 | 0% |
| K3 | 15'000 | 5% |
| K4 | 20'000 | 5% |
| K5 | 20'001 | 7% |
| K6 | 24'999 | 7% |
| K7 | 25'000 | 8,5% |
| K8 | 30'000 | 8,5% |