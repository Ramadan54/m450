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