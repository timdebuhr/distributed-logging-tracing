package de.tdbit.presentations.domain.address;

public class AddressAlreadyExistsException extends RuntimeException {

    private final String addressNumber;

    public AddressAlreadyExistsException(String addressNumber) {
        super("Address already exists for number: " + addressNumber);
        this.addressNumber = addressNumber;
    }

    public String getAddressNumber() {
        return addressNumber;
    }
}
