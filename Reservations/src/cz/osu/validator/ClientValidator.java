package cz.osu.validator;

import cz.osu.exception.EmptyValueException;
import cz.osu.exception.InvalidPhoneException;
import cz.osu.model.Client;

public class ClientValidator implements Validator<Client>{
    @Override
    public void validate(Client client) throws Exception {
        if(client == null){
            throw new EmptyValueException("Client cannot be null");
        }

        if(client.getName() == null || client.getName().isBlank()) {
            throw new EmptyValueException("Client name cannot be empty");
        }

        if(client.getPhone().length() != 9) {
            throw new InvalidPhoneException("Phone number must be 9 digits");
        }
        //if(client.getPhone().matches("\\d{9}")) {}
    }
}
