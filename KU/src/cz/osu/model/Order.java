package cz.osu.model;

import cz.osu.exception.EmptyValueException;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private LocalDateTime orderDate;
    private Customer customer;
    private List<ProductItem> items;

    public Order(Customer customer, List<ProductItem> items) throws EmptyValueException {
        if (customer == null) {
            throw new EmptyValueException("Zákazník musí existovat");
        }
        if (items == null || items.isEmpty()) {
            throw new EmptyValueException("Objednávka musí obsahovat položky");
        }
        this.orderDate = LocalDateTime.now();
        this.customer = customer;
        this.items = items;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }


    public List<ProductItem> getItems() {
        return items;
    }

    public double getOrderTotal() {
        return items.stream().mapToDouble(ProductItem::getTotalPrice).sum();
    }
}
