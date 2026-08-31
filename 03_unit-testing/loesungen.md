# Modul 450 – Unit Testing

Bearbeitet von: Ramadan Asani

---

## Aufgabe 1 – Simpler Rechner mit JUnit

Es wurde ein Maven-Projekt mit einer `Calculator`-Klasse erstellt, die die vier Grundrechenarten enthält (`add`, `subtract`, `multiply`, `divide`). Für die Division wird der Sonderfall "Division durch null" mit einer Exception abgefangen.

Im Test-Package wurde die Klasse `CalculatorTest` mit JUnit 5 erstellt. Verwendete Annotationen:

- `@Test` – markiert eine Methode als Testfall
- `@BeforeEach` – erstellt vor jedem Test einen neuen Calculator, damit die Tests unabhängig voneinander sind
- `@DisplayName` – gibt jedem Test einen lesbaren Namen im Report

Getestete Fälle: Addition (positiv und negativ), Subtraktion, Multiplikation (inkl. mal null), Division und Division durch null (erwartet eine Exception).

### Ausführung 1 – In der Entwicklungsumgebung (IntelliJ)

Alle 7 Tests wurden in IntelliJ ausgeführt und sind erfolgreich (grün).

![Tests in IntelliJ](screenshots/a1_tests_intellij.png)

### Ausführung 2 – Mit Maven

Die Tests wurden zusätzlich über den Maven-Lifecycle (`test`) ausgeführt. Ergebnis: `Tests run: 7, Failures: 0, Errors: 0` und `BUILD SUCCESS`.

![Tests mit Maven](screenshots/a1_tests_maven.png)

---

## Aufgabe 2 – JUnit Zusammenfassung

JUnit 5 ist das Standard-Framework für Unit-Tests in Java. Hier die gängigsten Features mit kurzen Beispielen.

### Annotationen

**`@Test`** – markiert eine Methode als Testfall.
```java
@Test
void testAddition() {
    assertEquals(4, calculator.add(2, 2));
}
```

**`@BeforeEach`** – wird vor jedem Test ausgeführt. Gut, um für jeden Test frische Objekte zu erstellen, damit die Tests unabhängig bleiben.
```java
@BeforeEach
void setUp() {
    calculator = new Calculator();
}
```

**`@AfterEach`** – wird nach jedem Test ausgeführt, z. B. zum Aufräumen (Dateien schliessen, Verbindungen trennen).

**`@BeforeAll` / `@AfterAll`** – werden einmal vor bzw. nach allen Tests der Klasse ausgeführt. Müssen `static` sein. Nützlich für aufwändiges Setup, das nur einmal nötig ist (z. B. Datenbankverbindung).

**`@DisplayName`** – gibt dem Test einen lesbaren Namen im Report.
```java
@Test
@DisplayName("Addition zweier positiver Zahlen")
void testAdd() { ... }
```

**`@Disabled`** – schaltet einen Test vorübergehend ab (er wird übersprungen).

### Assertions (Prüfungen)

Assertions prüfen, ob das Ergebnis dem Erwarteten entspricht. Schlägt eine fehl, gilt der Test als nicht bestanden.

| Assertion | Zweck |
|-----------|-------|
| `assertEquals(erwartet, ist)` | Prüft auf Gleichheit |
| `assertTrue(bedingung)` / `assertFalse(bedingung)` | Prüft einen booleschen Wert |
| `assertNull(obj)` / `assertNotNull(obj)` | Prüft auf null bzw. nicht null |
| `assertThrows(Exception.class, () -> ...)` | Prüft, ob eine Exception geworfen wird |
| `assertArrayEquals(erwartet, ist)` | Vergleicht zwei Arrays |

Beispiel für `assertThrows`:
```java
@Test
void testDivideByZero() {
    assertThrows(IllegalArgumentException.class, () -> calculator.divide(6, 0));
}
```

### Weitere nützliche Features

**`@ParameterizedTest`** – führt denselben Test mit verschiedenen Eingabewerten aus, statt für jeden Wert eine eigene Methode zu schreiben.
```java
@ParameterizedTest
@ValueSource(ints = {2, 4, 6})
void testIstGerade(int zahl) {
    assertTrue(zahl % 2 == 0);
}
```

**`assertAll`** – prüft mehrere Assertions zusammen und meldet alle Fehler auf einmal, statt beim ersten abzubrechen.

### Referenz

Als Nachschlagewerk eignet sich die offizielle JUnit-5-Dokumentation: https://junit.org/junit5/docs/current/user-guide/