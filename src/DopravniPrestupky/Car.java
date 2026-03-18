package DopravniPrestupky;

public class Car {
    private String spz;
    private int speed;
    private TypAkce typAkce;

    public Car(String spz, int speed, TypAkce typAkce) {
        this.spz = spz;
        this.speed = speed;
        this.typAkce = typAkce;
    }

    public String getSpz() {
        return spz;
    }

    public int getSpeed() {
        return speed;
    }

    public TypAkce getTypAkce() {
        return typAkce;
    }
}
