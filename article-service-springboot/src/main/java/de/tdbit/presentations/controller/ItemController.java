package de.tdbit.presentations.controller;

import de.tdbit.presentations.domain.item.Item;
import de.tdbit.presentations.domain.item.ItemService;
import de.tdbit.presentations.dto.ItemDto;
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
@RequestMapping("/api/items")
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class ItemController implements ItemControllerApi {

    private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);

    private ItemService itemService;

    public ItemController() {
        LOG.info("Creating controller: Item");
    }

    @Autowired
    public ItemController(ItemService service) {
        this();
        this.itemService = service;
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getItems() {
        LOG.info("Request items");
        List<Item> items = itemService.getAll();
        LOG.info("Loaded items: {}", items);
        return items.stream().map(ItemDto::new).collect(Collectors.toList());
    }

    @Override
    @GetMapping(value = "/{itemNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto getItem(@PathVariable("itemNumber") String itemNumber) {
        LOG.info("Request item by number: {}", itemNumber);
        Item item = itemService.get(itemNumber);
        return new ItemDto(item);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto addItem(@RequestBody ItemDto item) {
        LOG.info("Add item: {}", item);
        Item newItem = itemService.create(item.asEntity());
        return new ItemDto(newItem);
    }

    @Override
    @DeleteMapping(value = "/{itemNumber}")
    public ResponseEntity<Object> deleteItem(@PathVariable("itemNumber") String itemNumber) {
        LOG.info("Delete item by number: {}", itemNumber);
        itemService.remove(itemNumber);
        return ResponseEntity.noContent().build();
    }

}
