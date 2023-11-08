package de.tdbit.presentations.domain.item;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository repository;

    @Autowired
    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getAll() {
        return repository.getItems();
    }

    public Item get(String itemNumber) {
        Optional<Item> itemOptional = repository.getItem(itemNumber);
        if (itemOptional.isEmpty()) {
            throw new ItemNotFoundException(itemNumber);
        }
        return itemOptional.get();
    }

    @Transactional
    public Item create(Item item) {
        if (repository.exists(item.getItemNumber())) {
            throw new ItemAlreadyExistsException(item.getItemNumber());
        }
        return repository.store(item);
    }

    @Transactional
    public void remove(String itemNumber) {
        repository.delete(itemNumber);
    }
}
