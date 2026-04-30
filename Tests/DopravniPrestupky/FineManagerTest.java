package DopravniPrestupky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class FineManagerTest {
    private FineManager manager;

    @BeforeEach
    void setUp() {
        manager = new FineManager();
    }

    @Test
    void testDirectionFine() {
        // Nastavení lokace se značkou "Jen rovně"
        Location krizovatka = new Location("Křižovatka");
        // SignDirection.STRAIGHT
        krizovatka.addSign(new DirectionSign("Příkaz jízdy ",SignDirection.STRAIGHT));

        // Auto, které odbočuje (TypAkce.TURNING_LEFT)
        Car auto = new Car("ABC-01", 20, TypAkce.TURNING_LEFT);

        // Akce
        List<Fine> pokuty = manager.checkFines(auto, krizovatka);

        // Ověření: TypAkce "TURNING_LEFT" se nerovná SignDirection "STRAIGHT" -> Pokuta!
        assertFalse(pokuty.isEmpty(), "Auto mělo dostat pokutu za nedodržení směru.");
    }

    @Test
    void testSpeedLimitExactlyOnEdge() {
        Location zona30 = new Location("Zóna 30");
        zona30.addSign(new SpeedSign("Omezení rychlosti", 30));

        // Auto jede přesně 30 - to NENÍ přestupek
        Car auto = new Car("ABC-02", 30, TypAkce.DRIVING_STRAIGHT);

        List<Fine> pokuty = manager.checkFines(auto, zona30);

        assertTrue(pokuty.isEmpty(), "Jízda přesně na limitu by neměla být pokutována.");
    }

    @Test
    void testProhibitionFine() {
        // 1. Příprava: Lokace se zákazem parkování
        Location ulice = new Location("Modrá zóna");
        // Předpokládáme, že v ProhibitionSign i Car používáš stejný Enum TypAkce
        ulice.addSign(new ProhibitionSign("Zákaz stání", TypAkce.PARKING));

        // 2. Auto, které skutečně parkuje
        Car auto = new Car("8A5 4433", 0, TypAkce.PARKING);

        // 3. Akce
        List<Fine> pokuty = manager.checkFines(auto, ulice);

        // 4. Ověření
        assertFalse(pokuty.isEmpty(), "Auto parkující v zákazu musí dostat pokutu.");
    }

    @Test
    void testNoFineNoSign() {
        // Lokace bez značek
        Location volnaCesta = new Location("Volná cesta");

        // Auto jede rovně, ale žádné značky nejsou -> NENÍ přestupek
        Car auto = new Car("XYZ-123", 50, TypAkce.DRIVING_STRAIGHT);

        List<Fine> pokuty = manager.checkFines(auto, volnaCesta);

        assertTrue(pokuty.isEmpty());
    }
}