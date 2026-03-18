package DopravniPrestupky;

import java.util.ArrayList;
import java.util.List;

public class FineManager {

    public List<String> checkFines(Car car, Location location) {
        List<String> fines = new ArrayList<>();

        for (TrafficSign sign : location.getSigns()) {
            //Check for speed limit violation
            if (sign instanceof SpeedSign speedSign) {
                if (car.getSpeed() > speedSign.getSpeedLimit()) {
                    fines.add("Přestupek: " + car.getSpz() + " překročil rychlostní limit " + speedSign.getSpeedLimit() + " km/h na místě " + location.getName());
                }
            }
            //Check for prohibitions violation
            if (sign instanceof ProhibitionSign prohibitionSign) {
                if (car.getTypAkce() == prohibitionSign.getTypAkce()) {
                    fines.add("Přestupek: " + car.getSpz() + " porušil zákaz " + prohibitionSign.getTypAkce() + " na místě " + location.getName());

                }
            }
            //Check for direction violation
            if (sign instanceof DirectionSign ds) {
                if (car.getTypAkce().getOdpovidajiciSmer() != ds.getSignDirection()) {
                    fines.add("Pokuta za směr!");
                }
            }
        }
        return fines;
    }
}