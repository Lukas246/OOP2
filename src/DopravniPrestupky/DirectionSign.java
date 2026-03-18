package DopravniPrestupky;

public class DirectionSign extends TrafficSign{
    private SignDirection signDirection;

    public DirectionSign(String nazev, SignDirection signDirection) {
        super(nazev);
        this.signDirection = signDirection;
    }

    public SignDirection getSignDirection() {
        return signDirection;
    }

}
