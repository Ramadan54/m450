package ch.tbz.werkstatt.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TeilNichtVerfuegbarException extends RuntimeException {

    public TeilNichtVerfuegbarException(String teilenummer) {
        super("Teil " + teilenummer + " ist beim Lieferanten nicht verfügbar");
    }
}
