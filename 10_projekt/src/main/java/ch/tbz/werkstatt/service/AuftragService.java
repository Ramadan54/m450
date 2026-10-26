package ch.tbz.werkstatt.service;

import ch.tbz.werkstatt.client.TeileLieferantClient;
import ch.tbz.werkstatt.model.Auftrag;
import ch.tbz.werkstatt.repository.AuftragRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AuftragService {

    private final AuftragRepository auftragRepository;
    private final TeileLieferantClient teileLieferantClient;
    private final PreisRechner preisRechner;

    public AuftragService(AuftragRepository auftragRepository,
                          TeileLieferantClient teileLieferantClient,
                          PreisRechner preisRechner) {
        this.auftragRepository = auftragRepository;
        this.teileLieferantClient = teileLieferantClient;
        this.preisRechner = preisRechner;
    }

    public Auftrag erstelleAuftrag(Auftrag auftrag) {
        if (!teileLieferantClient.istTeilVerfuegbar(auftrag.getTeilenummer())) {
            throw new TeilNichtVerfuegbarException(auftrag.getTeilenummer());
        }

        BigDecimal preis = preisRechner.berechnePreis(
                auftrag.getArbeitsstunden(),
                auftrag.getTeilekosten(),
                auftrag.isStammkunde());
        auftrag.setPreis(preis);

        return auftragRepository.save(auftrag);
    }

    public Auftrag findeAuftrag(Long id) {
        return auftragRepository.findById(id)
                .orElseThrow(() -> new AuftragNichtGefundenException(id));
    }
}
