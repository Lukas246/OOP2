package cz.osu.model;

import java.time.LocalDateTime;
import java.util.List;

public class Reservation {
    private LocalDateTime date;
    private List<Activity> activities;
    private Client client;

    public Reservation(LocalDateTime date, List<Activity> activities, Client client) {
        this.date = date;
        this.activities = activities;
        this.client = client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public Client getClient() {
        return client;
    }
}
