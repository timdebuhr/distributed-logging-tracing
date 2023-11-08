package de.tdbit.presentations.domain;

import de.tdbit.presentations.domain.item.Item;
import de.tdbit.presentations.domain.item.ItemAlreadyExistsException;
import de.tdbit.presentations.domain.item.ItemRepository;
import de.tdbit.presentations.domain.item.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemServiceTest {

    private ItemService serviceUnderTest;
    private ItemRepository repoMock;


    @BeforeEach
    public void setUp() {
        repoMock = mock(ItemRepository.class);
        serviceUnderTest = new ItemService(repoMock);
    }

    @Test
    public void testCreate() {
        Item expected = new Item("ART_2");
        when(repoMock.exists(anyString())).thenReturn(false);
        when(repoMock.store(any(Item.class))).thenReturn(expected);
        Item created = serviceUnderTest.create(new Item("ART_2"));

        assertEquals(expected, created);
    }

    @Test
    public void testCreateFailed() {
        when(repoMock.exists(anyString())).thenReturn(true);
        try {
            serviceUnderTest.create(new Item("ART_2"));
            fail("unexpected success");
        } catch (ItemAlreadyExistsException e) {
            // valid
            assertEquals("ART_2", e.getItemNumber());
        }
    }
}
