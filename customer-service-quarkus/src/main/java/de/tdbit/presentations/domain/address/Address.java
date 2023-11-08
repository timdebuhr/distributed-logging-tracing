package de.tdbit.presentations.domain.address;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {

    private String addressNumber;
    private String addressLineOne;
    private String addressLineTwo;
    private String addressLineThree;
    private String addressLineFour;

    protected Address() {
        // for framework
    }

    public Address(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public String getAddressLineThree() {
        return addressLineThree;
    }

    public String getAddressLineFour() {
        return addressLineFour;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public void setAddressLineThree(String addressLineThree) {
        this.addressLineThree = addressLineThree;
    }

    public void setAddressLineFour(String addressLineFour) {
        this.addressLineFour = addressLineFour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address item)) return false;
        return Objects.equals(getAddressNumber(), item.getAddressNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddressNumber());
    }

    @Override
    public String toString() {
        return "Address{"
                + "addressNumber='" + addressNumber + "'"
                + ", addressLineOne='" + addressLineOne + "'"
                + ", addressLineTwo='" + addressLineTwo + "'"
                + ", addressLineThree='" + addressLineThree + "'"
                + ", addressLineFour='" + addressLineFour + "'"
                + "}";
    }
}
