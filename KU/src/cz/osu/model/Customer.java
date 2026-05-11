package cz.osu.model;

import cz.osu.exception.EmptyValueException;
import cz.osu.exception.InvalidEmailException;

public class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) throws EmptyValueException, InvalidEmailException {
        setName(name);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws EmptyValueException {
        if (name == null || name.trim().isEmpty()) {
            throw new EmptyValueException("Jméno " + name + " nemůže být prázdné.");
        }
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmptyValueException, InvalidEmailException {
        if (email == null || email.trim().isEmpty()) {
            throw new EmptyValueException("Email nemůže být prázdný.");
        }
        String em = email.trim();
        if (!em.contains("@") || !(em.endsWith(".cz") || em.endsWith(".com"))) {
            throw new InvalidEmailException(email + " není platný formát emailu. Musí obsahovat '@' a končit na '.cz' nebo '.com'.");
        }
        this.email = email;
    }
}
