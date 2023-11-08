package de.tdbit.presentations.controller;

import de.tdbit.presentations.domain.item.ItemAlreadyExistsException;
import de.tdbit.presentations.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemAlreadyExistsMapper {

    public static final Logger LOG = LoggerFactory.getLogger(ItemAlreadyExistsMapper.class);

    public ItemAlreadyExistsMapper() {
        LOG.info("Creating exception mapper for type: ItemAlreadyExists");
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> toResponse(ItemAlreadyExistsException e) {
        LOG.debug("ItemAlreadyExistsMapper: Details zum erwartet gefangenen Fehler: " + e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorDto(e.getItemNumber(), e.getMessage()));
    }
}
