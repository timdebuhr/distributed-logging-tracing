package de.tdbit.presentations.controller;

import de.tdbit.presentations.domain.item.ItemNotFoundException;
import de.tdbit.presentations.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemNotFoundMapper {

    public static final Logger LOG = LoggerFactory.getLogger(ItemNotFoundMapper.class);

    public ItemNotFoundMapper() {
        LOG.info("Creating exception mapper for type: ItemNotFound");
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> toResponse(ItemNotFoundException e) {
        LOG.debug("ItemNotFoundMapper: Details zum erwartet gefangenen Fehler: " + e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorDto(e.getItemNumber(), e.getMessage()));
    }
}
