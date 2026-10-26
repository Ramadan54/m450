package ch.tbz.werkstatt.service;

import ch.tbz.werkstatt.client.TeileLieferantClient;
import ch.tbz.werkstatt.model.Auftrag;
import ch.tbz.werkstatt.repository.AuftragRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuftragServiceTest {

    @Mock
    private AuftragRepository auftragRepository;

    @Mock
    private TeileLieferantClient teileLieferantClient;

    private AuftragService auftragService;

    @BeforeEach
    void setUp() {
        // Mocks für Repository und Lieferant, echter PreisRechner
        auftragService = new AuftragService(auftragRepository, teileLieferantClient, new PreisRechner());
    }

    private Auftrag beispielAuftrag() {
        return new Auftrag("Max Muster", "VW Golf GTI", "TURBO-001",
                new BigDecimal("2"), new BigDecimal("150.00"), false);
    }

    @Test
    void erstelleAuftrag_teilVerfuegbar_berechnetPreisUndSpeichert() {
        Auftrag auftrag = beispielAuftrag();
        when(teileLieferantClient.istTeilVerfuegbar("TURBO-001")).thenReturn(true);
        when(auftragRepository.save(any(Auftrag.class))).thenAnswer(aufruf -> aufruf.getArgument(0));

        Auftrag gespeichert = auftragService.erstelleAuftrag(auftrag);

        // 2 h × 120 CHF + 150 CHF = 390 CHF
        assertThat(gespeichert.getPreis()).isEqualByComparingTo("390.00");
        verify(teileLieferantClient).istTeilVerfuegbar("TURBO-001");
        verify(auftragRepository).save(auftrag);
    }

    @Test
    void erstelleAuftrag_teilNichtVerfuegbar_wirftExceptionUndSpeichertNicht() {
        Auftrag auftrag = beispielAuftrag();
        when(teileLieferantClient.istTeilVerfuegbar("TURBO-001")).thenReturn(false);

        assertThatThrownBy(() -> auftragService.erstelleAuftrag(auftrag))
                .isInstanceOf(TeilNichtVerfuegbarException.class)
                .hasMessageContaining("TURBO-001");

        verify(auftragRepository, never()).save(any());
    }

    @Test
    void findeAuftrag_vorhanden_gibtAuftragZurueck() {
        Auftrag auftrag = beispielAuftrag();
        auftrag.setId(1L);
        when(auftragRepository.findById(1L)).thenReturn(Optional.of(auftrag));

        Auftrag gefunden = auftragService.findeAuftrag(1L);

        assertThat(gefunden.getId()).isEqualTo(1L);
        assertThat(gefunden.getKundenname()).isEqualTo("Max Muster");
    }

    @Test
    void findeAuftrag_nichtVorhanden_wirftException() {
        when(auftragRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> auftragService.findeAuftrag(99L))
                .isInstanceOf(AuftragNichtGefundenException.class)
                .hasMessageContaining("99");
    }
}
