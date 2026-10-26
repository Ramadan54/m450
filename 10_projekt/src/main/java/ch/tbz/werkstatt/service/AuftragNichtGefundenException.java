package ch.tbz.werkstatt.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuftragNichtGefundenException extends RuntimeException {

    public AuftragNichtGefundenException(Long id) {
        super("Auftrag mit ID " + id + " wurde nicht gefunden");
    }
}
