package de.tdbit.presentations.controller;

import de.tdbit.presentations.domain.address.AddressNotFoundException;
import de.tdbit.presentations.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AddressNotFoundMapper {

    public static final Logger LOG = LoggerFactory.getLogger(AddressNotFoundMapper.class);

    public AddressNotFoundMapper() {
        LOG.info("Creating exception mapper for type: AddressNotFound");
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<Object> toResponse(AddressNotFoundException e) {
        LOG.debug("AddressNotFoundMapper: Details zum erwartet gefangenen Fehler: " + e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorDto(e.getAddressNumber(), e.getMessage()));
    }
}
