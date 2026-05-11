package cz.osu.model;

import cz.osu.exception.EmptyValueException;
import cz.osu.exception.NegativeValueException;
import cz.osu.exception.ShortProductNameException;

public class ProductItem {
    private String name;
    private double price;
    private int quantity;

    public ProductItem(String name, double price, int quantity) throws EmptyValueException, ShortProductNameException, NegativeValueException {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws EmptyValueException, ShortProductNameException {
        if (name == null || name.trim().isEmpty()) {
            throw new EmptyValueException("Název produktu nemůže být prázdný.");
        }
        if (name.trim().length() < 3) {
            throw new ShortProductNameException(name + " je příliš krátké jméno. Název produktu musí být alespoň 3 znaky dlouhý.");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws NegativeValueException {
        if (price < 0) {
            throw new NegativeValueException(price + " není validní. Cena nemůže být záporná.");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws NegativeValueException {
        try {
            if (quantity < 0) {
                throw new NegativeValueException(quantity + "není validní. Množství nemůže být záporné.");
            }
            this.quantity = quantity;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(quantity + "není validní množství. Množství musí být celé číslo.");
        }
    }
    public double getTotalPrice() {
        return price * quantity;
    }
}
