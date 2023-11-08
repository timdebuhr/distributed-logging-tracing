package de.tdbit.presentations.dto;

import de.tdbit.presentations.domain.Order;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbNillable;
import jakarta.json.bind.annotation.JsonbProperty;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Bestellung", description = "Eine Bestellung")
public record OrderDto(
        @Schema(description = "Die Bestellnummer", type = SchemaType.STRING, required = true, example = "ORD_1")
        @JsonbProperty("orderNumber")
        String orderNumber,
        @Schema(description = "Die Kunde der Bestellung", type = SchemaType.OBJECT, implementation = CustomerDto.class)
        @JsonbProperty("customer")
        CustomerDto customer,
        @Schema(description = "Die Artikel der Bestellung", type = SchemaType.ARRAY, implementation = ItemDto.class)
        @JsonbProperty("items") @JsonbNillable
        List<ItemDto> items
) implements Serializable {

    @JsonbCreator
    public OrderDto(String orderNumber,
                    CustomerDto customer,
                    List<ItemDto> items) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.items = items;
    }

    public OrderDto(Order order) {
        this(order.getOrderNumber(),
                order.getCustomer() != null ? new CustomerDto(order.getCustomer()) : null,
                order.getItems() != null ? order.getItems().stream().map(ItemDto::new).toList() : null);
    }

    public Order asEntity() {
        Order order = new Order(orderNumber);
        order.add(customer.asEntity());
        if (items != null) {
            items.forEach(item -> {
                order.add(item.asEntity());
            });
        }
        return order;
    }

}
