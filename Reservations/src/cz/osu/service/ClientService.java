package cz.osu.service;

import cz.osu.exception.EmptyValueException;
import cz.osu.exception.InvalidPhoneException;
import cz.osu.model.Client;
import cz.osu.validator.Validator;

public class ClientService {
    private Validator<Client> clientValidator;


    public ClientService(Validator<Client> clientValidator) {
        this.clientValidator = clientValidator;
    }

    public void process(Client client) {
        try {
            this.clientValidator.validate(client);
        } catch (EmptyValueException e) {
            System.out.println("Chyba (prázdná): " + e.getMessage());
        }
        catch (InvalidPhoneException e) {
            System.out.println("Chyba (telefon): " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Chyba (obecná): " + e.getMessage());
        }

    }
}
