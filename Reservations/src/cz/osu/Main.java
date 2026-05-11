package cz.osu;

import cz.osu.model.Activity;
import cz.osu.model.Client;
import cz.osu.model.Reservation;
import cz.osu.service.ClientService;
import cz.osu.validator.ClientValidator;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    static void main() {
        Client client = new Client("John", "123456789");
        ClientService clientService = new ClientService(new ClientValidator());
        clientService.process(client);

        Activity activity1 = new Activity("Yoga", 60, 100.7);
        Activity activity2 = new Activity("Swimming", 30, 120.5);

        Reservation reservation = new Reservation(LocalDateTime.now(), List.of(activity1, activity2), client);
    }
}
