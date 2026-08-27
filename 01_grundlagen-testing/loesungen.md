# Modul 450 – Grundlagen zu Testing

Bearbeitet von: Ramadan Asani

---

## Aufgabe 1 – Testformen in der Informatik

Drei Testarten, die ich aus der Praxis kenne (Beispiele aus unserem NoQui-Quiz-Projekt):

**1. Unit-Test**
Prüft eine einzelne Methode isoliert, z. B. die Punkteberechnung im Backend.
Durchführung: automatisiert mit einem Testframework (z. B. JUnit oder Jest). Der Test ruft die Methode mit festen Eingaben auf und vergleicht das Resultat mit dem erwarteten Wert.

**2. Integrationstest**
Prüft, ob mehrere Komponenten zusammenspielen, z. B. ob das Frontend die Fragen korrekt vom Backend lädt.
Durchführung: manuell mit einem REST-Client wie Postman oder automatisiert mit einem Test, der Frontend und Backend zusammen prüft.

**3. Systemtest (End-to-End)**
Prüft das ganze System, z. B. einen kompletten Quiz-Durchlauf: Raum erstellen, beitreten, Fragen beantworten, Rangliste anschauen.
Durchführung: manuell nach einer Testfall-Liste oder automatisiert mit einem Browser-Tool wie Cypress oder Selenium.

---

## Aufgabe 2 – Fehler, Mangel und hoher Schaden

**Beispiel für einen Fehler**
Im NoQui-Quiz berechnet das System bei einer richtigen Antwort 0 statt der erwarteten Punkte. Das SOLL-Verhalten (Punkte gutschreiben) weicht vom IST-Verhalten (keine Punkte) ab. Eine Anforderung wird also nicht erfüllt → Fehler.

**Beispiel für einen Mangel**
Die Punkte werden korrekt berechnet, aber in der Rangliste falsch dargestellt (z. B. abgeschnitten oder in falscher Reihenfolge angezeigt). Die Berechnung stimmt, nur die Erwartung an eine saubere Anzeige wird nicht angemessen erfüllt → Mangel.

**Beispiel für einen hohen Schaden bei einem Fehler**
Bei einer Software für ein Bremssystem im Auto: Ein Fehler in der Steuerung kann dazu führen, dass die Bremse nicht auslöst. Das gefährdet Menschenleben. Solche Systeme müssen deshalb viel ausgiebiger getestet werden als z. B. ein Quiz-Spiel.