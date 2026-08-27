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

| ID  | Bedingung (Kaufpreis)   | Erwarteter Rabatt |
| --- | ----------------------- | ----------------- |
| A1  | preis < 15'000          | 0%                |
| A2  | 15'000 ≤ preis ≤ 20'000 | 5%                |
| A3  | 20'000 < preis < 25'000 | 7%                |
| A4  | preis ≥ 25'000          | 8,5%              |

### Konkrete Testfälle

Hier werden echte Eingabewerte verwendet. Bewusst an den Grenzen gewählt, weil dort am ehesten Fehler auftreten (Grenzwertanalyse).

| ID  | Kaufpreis (CHF) | Erwarteter Rabatt |
| --- | --------------- | ----------------- |
| K1  | 10'000          | 0%                |
| K2  | 14'999          | 0%                |
| K3  | 15'000          | 5%                |
| K4  | 20'000          | 5%                |
| K5  | 20'001          | 7%                |
| K6  | 24'999          | 7%                |
| K7  | 25'000          | 8,5%              |
| K8  | 30'000          | 8,5%              |

---

## Übung 2 – Funktionale Black-Box-Testfälle Autovermietung

Getestete Webseite: Europcar Schweiz (www.europcar.ch)

Es handelt sich um Black-Box-Tests: Getestet wird nur das sichtbare Verhalten der Webseite als Benutzer, ohne den Code zu kennen. Die Testfälle beziehen sich auf tatsächlich vorhandene Funktionen der Seite (Online-Buchung mit Stationsauswahl, Fahrzeugart, Fahreralter).

| ID  | Beschreibung                                                  | Erwartetes Resultat                                                                         | Effektives Resultat | Status | Mögliche Ursache |
| --- | ------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------- | ------ | ---------------- |
| 1   | Startseite aufrufen                                           | Die Seite lädt und zeigt die Buchungsmaske mit Feldern für Ort, Abhol- und Rückgabedatum an | Wie erwartet        | OK     | –                |
| 2   | Abhol-/Rückgabeort (z. B. Zürich) eingeben                    | Es werden passende Europcar-Stationen zur Auswahl angezeigt                                 | Wie erwartet        | OK     | –                |
| 3   | Auf "Suche" klicken, ohne Ort und Datum einzugeben            | Die Suche startet nicht; es werden Hinweise auf die fehlenden Pflichtfelder angezeigt       | Wie erwartet        | OK     | –                |
| 4   | Fahrzeugart von "Pkw" auf "Camper & Nutzfahrzeuge" umschalten | Die Auswahl wechselt sichtbar auf Camper & Nutzfahrzeuge                                    | Wie erwartet        | OK     | –                |
| 5   | Bei "Ich bin 26+" das Fahreralter ändern                      | Es öffnet sich eine Auswahl mit Altersstufen von 18 bis 26+                                 | Wie erwartet        | OK     | –                |

### Nachweise (Screenshots)

**Testfall 1 – Startseite mit Buchungsmaske:**

![Testfall 1](screenshots/u2_europcar_startseite.png)

**Testfall 2 – Ort "Zürich" eingegeben, Stationen werden angezeigt:**

![Testfall 2](screenshots/u2_europcar_suche.png)

**Testfall 3 – Suche ohne Ort und Datum, Hinweise auf Pflichtfelder:**

![Testfall 3](screenshots/u2_ohne_ort.png)

**Testfall 4 – Umschalten auf Camper & Nutzfahrzeuge:**

![Testfall 4](screenshots/u2_nutzfahrzeuge.png)

**Testfall 5 – Fahreralter ändern (Auswahl 18 bis 26+):**

![Testfall 5](screenshots/u2_talter.png)
