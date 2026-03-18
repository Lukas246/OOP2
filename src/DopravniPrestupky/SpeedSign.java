package DopravniPrestupky;

public class SpeedSign extends TrafficSign{
    private int speedLimit;

    public SpeedSign(String nazev, int speedLimit) {
        super(nazev);
        this.speedLimit = speedLimit;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }
}
