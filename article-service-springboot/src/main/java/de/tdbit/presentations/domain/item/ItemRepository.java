package de.tdbit.presentations.domain.item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Item> getItems() {
        return entityManager.createNamedQuery(Item.GET_ALL, Item.class)
                .getResultList();
    }

    public Optional<Item> getItem(String itemNumber) {
        return entityManager.createNamedQuery(Item.FIND_BY_ITEM_NUMBER, Item.class)
                .setParameter(Item.PARAM_ITEM_NUMBER, itemNumber)
                .getResultList()
                .stream()
                .findFirst();
    }

    public Item store(Item item) {
        return entityManager.merge(item);
    }

    public void delete(String itemNumber) {
        entityManager.createNamedQuery(Item.DELETE_BY_ITEM_NUMBER)
                .setParameter(Item.PARAM_ITEM_NUMBER, itemNumber)
                .executeUpdate();
    }

    public boolean exists(String itemNumber) {
        return !entityManager.createNamedQuery(Item.CHECK_EXIST_BY_ITEM_NUMBER, String.class)
                .setParameter(Item.PARAM_ITEM_NUMBER, itemNumber)
                .getResultList()
                .isEmpty();
    }
}
