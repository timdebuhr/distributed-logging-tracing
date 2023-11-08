package de.tdbit.presentations.controller;

import de.tdbit.presentations.domain.address.AddressAlreadyExistsException;
import de.tdbit.presentations.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AddressAlreadyExistsMapper {

    public static final Logger LOG = LoggerFactory.getLogger(AddressAlreadyExistsMapper.class);

    public AddressAlreadyExistsMapper() {
        LOG.info("Creating exception mapper for type: AddressAlreadyExists");
    }

    @ExceptionHandler(AddressAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> toResponse(AddressAlreadyExistsException e) {
        LOG.debug("AddressAlreadyExistsMapper: Details zum erwartet gefangenen Fehler: " + e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorDto(e.getAddressNumber(), e.getMessage()));
    }
}
