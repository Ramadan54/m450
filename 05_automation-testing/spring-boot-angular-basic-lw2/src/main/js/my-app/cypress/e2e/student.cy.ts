// E2E-Test für die Studenten-App (Angular Frontend)
// Getestet wird das sichtbare Verhalten im Browser, wie ein echter Benutzer.

describe('Studenten-App E2E', () => {
  // Vor jedem Test die Startseite öffnen
  beforeEach(() => {
    cy.visit('/');
  });

  it('zeigt die Startseite mit den beiden Buttons', () => {
    // Die beiden Navigations-Buttons müssen sichtbar sein
    cy.contains('List Students').should('be.visible');
    cy.contains('Add Students').should('be.visible');
  });

  it('zeigt die 5 Standard-Studenten in der Liste', () => {
    // Zur Liste navigieren
    cy.contains('List Students').click();

    // Die 5 vorangelegten Studenten müssen in der Tabelle stehen
    cy.contains('Jonas').should('be.visible');
    cy.contains('Patrick').should('be.visible');
    cy.contains('Yves').should('be.visible');
    cy.contains('Peter').should('be.visible');
    cy.contains('Ann').should('be.visible');
  });

  it('legt einen neuen Studenten über das Formular an', () => {
    // Eindeutigen Namen erzeugen, damit der Test beliebig oft wiederholbar ist
    const eindeutigerName = 'CypressTest_' + Date.now();
    const email = eindeutigerName.toLowerCase() + '@tbz.ch';

    // Zum Formular navigieren
    cy.contains('Add Students').click();

    // Formular ausfüllen (die Felder haben die IDs name und email)
    cy.get('#name').type(eindeutigerName);
    cy.get('#email').type(email);

    // Absenden
    cy.contains('Submit').click();

    // Nach dem Absenden leitet die App automatisch auf die Liste um (/students)
    cy.url().should('include', '/students');

    // Der neu angelegte Student muss jetzt in der Liste erscheinen
    cy.contains(eindeutigerName).should('be.visible');
    cy.contains(email).should('be.visible');
  });
});
