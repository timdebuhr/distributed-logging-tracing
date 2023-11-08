package de.tdbit.presentations.domain.address;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AddressRepository {

    private final Map<String, Address> storage = new ConcurrentHashMap<>();

    public List<Address> getAddresses() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Address> getAddress(String addressNumber) {
        return Optional.ofNullable(storage.get(addressNumber));
    }

    public Address store(Address address) {
        address.prepareToStore();
        return storage.put(address.getAddressNumber(), address);
    }

    public void delete(String addressNumber) {
        storage.remove(addressNumber);
    }

    public boolean exists(String addressNumber) {
        return storage.containsKey(addressNumber);
    }

    @PostConstruct
    public void init() {
        Address address = new Address("ADD_1");
        address.setAddressLineOne("Max Mustermann");
        address.setAddressLineTwo("Beispielstrasse 123");
        address.setAddressLineThree("12345 Beispielhausen");
        store(address);
    }
}
