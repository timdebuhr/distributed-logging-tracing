package de.tdbit.presentations.dto;

public class ErrorDto {

    String artNumber;

    String message;

    public ErrorDto(String artNumber, String message) {
        this.artNumber = artNumber;
        this.message = message;
    }

    public String getArtNumber() {
        return artNumber;
    }

    public String getMessage() {
        return message;
    }
}
