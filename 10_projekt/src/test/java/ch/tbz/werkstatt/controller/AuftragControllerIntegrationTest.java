package ch.tbz.werkstatt.controller;

import ch.tbz.werkstatt.client.TeileLieferantClient;
import ch.tbz.werkstatt.model.Auftrag;
import ch.tbz.werkstatt.repository.AuftragRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuftragControllerIntegrationTest {

    private static final String GUELTIGER_AUFTRAG = """
            {
              "kundenname": "Max Muster",
              "fahrzeug": "VW Golf GTI",
              "teilenummer": "TURBO-001",
              "arbeitsstunden": 10,
              "teilekosten": 300,
              "stammkunde": true
            }
            """;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuftragRepository auftragRepository;

    @MockitoBean
    private TeileLieferantClient teileLieferantClient;

    @BeforeEach
    void setUp() {
        // Jeder Test startet mit leerer Datenbank
        auftragRepository.deleteAll();
    }

    @Test
    void postAuftrag_gueltigeDaten_gibt201UndSpeichertMitPreis() throws Exception {
        when(teileLieferantClient.istTeilVerfuegbar("TURBO-001")).thenReturn(true);

        mockMvc.perform(post("/auftraege")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(GUELTIGER_AUFTRAG))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.kundenname").value("Max Muster"))
                .andExpect(jsonPath("$.fahrzeug").value("VW Golf GTI"))
                // 1500 CHF − 15 % (Mengen- + Stammkundenrabatt) = 1275 CHF
                .andExpect(jsonPath("$.preis").value(1275.0));

        // Prüfen, dass der Auftrag wirklich in der Datenbank gelandet ist
        assertThat(auftragRepository.count()).isEqualTo(1);
    }

    @Test
    void postAuftrag_teilNichtVerfuegbar_gibt409UndSpeichertNicht() throws Exception {
        when(teileLieferantClient.istTeilVerfuegbar("TURBO-001")).thenReturn(false);

        mockMvc.perform(post("/auftraege")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(GUELTIGER_AUFTRAG))
                .andExpect(status().isConflict());

        assertThat(auftragRepository.count()).isZero();
    }

    @Test
    void postAuftrag_ungueltigeDaten_gibt400OhneLieferantenAufruf() throws Exception {
        String ungueltigerAuftrag = """
                {
                  "kundenname": "",
                  "fahrzeug": "VW Golf GTI",
                  "teilenummer": "TURBO-001",
                  "arbeitsstunden": -5,
                  "teilekosten": 300,
                  "stammkunde": false
                }
                """;

        mockMvc.perform(post("/auftraege")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ungueltigerAuftrag))
                .andExpect(status().isBadRequest());

        // Validierung greift schon am Eingang, der Lieferant wird gar nicht erst gefragt
        verifyNoInteractions(teileLieferantClient);
    }

    @Test
    void getAuftrag_vorhandeneId_gibt200MitAuftrag() throws Exception {
        Auftrag auftrag = new Auftrag("Anna Beispiel", "BMW M3", "BREMSE-003",
                new BigDecimal("3"), new BigDecimal("200.00"), false);
        auftrag.setPreis(new BigDecimal("560.00"));
        Auftrag gespeichert = auftragRepository.save(auftrag);

        mockMvc.perform(get("/auftraege/" + gespeichert.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kundenname").value("Anna Beispiel"))
                .andExpect(jsonPath("$.fahrzeug").value("BMW M3"))
                .andExpect(jsonPath("$.preis").value(560.0));
    }

    @Test
    void getAuftrag_unbekannteId_gibt404() throws Exception {
        mockMvc.perform(get("/auftraege/9999"))
                .andExpect(status().isNotFound());
    }
}
