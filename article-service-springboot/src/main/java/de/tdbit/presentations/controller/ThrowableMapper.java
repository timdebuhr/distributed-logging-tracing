package de.tdbit.presentations.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ThrowableMapper {

    public static final Logger LOG = LoggerFactory.getLogger(ThrowableMapper.class);

    public ThrowableMapper() {
        LOG.info("Creating exception mapper for type: Throwable");
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> toResponse(Throwable e) {
        LOG.error("ThrowableMapper: Details zum unerwartet gefangenen Fehler: " + e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\": \""+ e.getMessage() + "\"}");
    }
}
