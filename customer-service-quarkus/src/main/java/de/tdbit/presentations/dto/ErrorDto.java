package de.tdbit.presentations.dto;

import java.io.Serializable;

public record ErrorDto(String cusNumber,
                       String message) implements Serializable {

}
