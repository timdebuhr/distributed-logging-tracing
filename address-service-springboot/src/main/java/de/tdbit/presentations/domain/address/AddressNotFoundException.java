package de.tdbit.presentations.domain.address;

public class AddressNotFoundException extends RuntimeException {

    private final String addressNumber;

    public AddressNotFoundException(String addressNumber) {
        super("No address found for number: " + addressNumber);
        this.addressNumber = addressNumber;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

}
