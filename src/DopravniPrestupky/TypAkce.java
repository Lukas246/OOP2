package DopravniPrestupky;

public enum TypAkce {
    TURNING_LEFT(SignDirection.LEFT),
    TURNING_RIGHT(SignDirection.RIGHT),
    DRIVING_STRAIGHT(SignDirection.STRAIGHT),
    PARKING(null);

    private final SignDirection odpovidajiciSmer;

    TypAkce(SignDirection smer) {
        this.odpovidajiciSmer = smer;
    }


    public SignDirection getOdpovidajiciSmer() {
        return odpovidajiciSmer;
    }
}
