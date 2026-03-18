package DopravniPrestupky;

public class ProhibitionSign extends TrafficSign{
    private TypAkce typAkce;

    public ProhibitionSign(String nazev, TypAkce typAkce) {
        super(nazev);
        this.typAkce = typAkce;
    }

    public TypAkce getTypAkce() {
        return typAkce;
    }
}
