package de.tdbit.presentations.domain.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> getAll() {
        return repository.getAddresses();
    }

    public Address get(String addressNumber) {
        Optional<Address> addressOptional = repository.getAddress(addressNumber);
        if (addressOptional.isEmpty()) {
            throw new AddressNotFoundException(addressNumber);
        }
        return addressOptional.get();
    }

    public Address create(Address address) {
        if (repository.exists(address.getAddressNumber())) {
            throw new AddressAlreadyExistsException(address.getAddressNumber());
        }
        return repository.store(address);
    }

    public void remove(String addressNumber) {
        repository.delete(addressNumber);
    }
}
