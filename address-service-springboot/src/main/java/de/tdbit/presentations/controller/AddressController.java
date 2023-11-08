package de.tdbit.presentations.controller;

import de.tdbit.presentations.domain.address.Address;
import de.tdbit.presentations.domain.address.AddressService;
import de.tdbit.presentations.dto.AddressDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/addresses")
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class AddressController implements AddressControllerApi {

    private static final Logger LOG = LoggerFactory.getLogger(AddressController.class);

    private AddressService addressService;

    public AddressController() {
        LOG.info("Creating controller: Address");
    }

    @Autowired
    public AddressController(AddressService service) {
        this();
        this.addressService = service;
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AddressDto> getAddresses() {
        LOG.info("Request addresses");
        List<Address> addresses = addressService.getAll();
        LOG.info("Loaded addresses: {}", addresses);
        return addresses.stream().map(AddressDto::new).collect(Collectors.toList());
    }

    @Override
    @GetMapping(value = "/{addressNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressDto getAddress(@PathVariable("addressNumber") String addressNumber) {
        LOG.info("Request address by number: {}", addressNumber);
        Address address = addressService.get(addressNumber);
        return new AddressDto(address);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressDto addAddress(@RequestBody AddressDto address) {
        LOG.info("Add address: {}", address);
        Address newAddress = addressService.create(address.asEntity());
        return new AddressDto(newAddress);
    }

    @Override
    @DeleteMapping(value = "/{addressNumber}")
    public ResponseEntity<Object> deleteAddress(@PathVariable("addressNumber") String addressNumber) {
        LOG.info("Delete address by number: {}", addressNumber);
        addressService.remove(addressNumber);
        return ResponseEntity.noContent().build();
    }

}
