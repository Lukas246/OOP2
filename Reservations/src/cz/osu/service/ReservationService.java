package cz.osu.service;

import cz.osu.model.Activity;
import cz.osu.model.Reservation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationService {
    public static String getMostFrequentActivity(List<Reservation> reservations) {

        return reservations
                .stream()
                .flatMap(reservation -> reservation.getActivities().stream())
                .collect(Collectors.groupingBy(Activity::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow()
                .getKey();
    }
}
