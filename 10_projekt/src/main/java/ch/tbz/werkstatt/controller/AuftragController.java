package ch.tbz.werkstatt.controller;

import ch.tbz.werkstatt.model.Auftrag;
import ch.tbz.werkstatt.service.AuftragService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auftraege")
public class AuftragController {

    private final AuftragService auftragService;

    public AuftragController(AuftragService auftragService) {
        this.auftragService = auftragService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Auftrag erstelleAuftrag(@Valid @RequestBody AuftragRequest request) {
        return auftragService.erstelleAuftrag(request.toAuftrag());
    }

    @GetMapping("/{id}")
    public Auftrag findeAuftrag(@PathVariable Long id) {
        return auftragService.findeAuftrag(id);
    }
}
