package DopravniPrestupky;

import java.util.ArrayList;
import java.util.List;

public class FineManager {

    public List<Fine> checkFines(Car car, Location location) {
        List<Fine> fines = new ArrayList<>();

        for (TrafficSign sign : location.getSigns()) {
            //Check for speed limit violation
            if (sign instanceof SpeedSign speedSign) {
                if (car.getSpeed() > speedSign.getSpeedLimit()) {
                    fines.add(new Fine(1000, "Přestupek: " + car.getSpz() + " překročil rychlostní limit " + speedSign.getSpeedLimit() + " km/h na místě " + location.getName()));
                }
            }
            //Check for prohibitions violation
            if (sign instanceof ProhibitionSign prohibitionSign) {
                if (car.getTypAkce() == prohibitionSign.getTypAkce()) {
                    fines.add(new Fine(1500, "Přestupek: " + car.getSpz() + " porušil zákaz " + prohibitionSign.getTypAkce() + " na místě " + location.getName()));

                }
            }
            //Check for direction violation
            if (sign instanceof DirectionSign ds) {
                if (car.getTypAkce().getOdpovidajiciSmer() != ds.getSignDirection()) {
                    fines.add(new Fine(2000, "Přestupek: " + car.getSpz() + " nedodržel směr " + ds.getSignDirection() + " na místě " + location.getName()));
                }
            }
        }
        return fines;
    }
}