import cz.osu.model.Customer;
import cz.osu.model.Order;
import cz.osu.model.ProductItem;
import cz.osu.exception.EmptyValueException;
import cz.osu.exception.InvalidEmailException;
import cz.osu.exception.NegativeValueException;
import cz.osu.exception.ShortProductNameException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ValidationTest {

    // ==========================================
    // Testy pro třídu Customer
    // ==========================================

    @Test
    public void testCustomerEmptyNameThrowsException() {
        // Pokud zadáme prázdné jméno, očekáváme EmptyValueError
        Exception exception = assertThrows(EmptyValueException.class, () -> new Customer("", "jan@novak.cz"));
    }

    @Test
    public void testCustomerInvalidEmailThrowsException() {
        // Chybějící zavináč, špatná koncovka
        assertThrows(InvalidEmailException.class, () -> new Customer("Jan Novák", "jannovak.cz"));
        assertThrows(InvalidEmailException.class, () -> new Customer("Jan Novák", "jan@novak"));
    }


    // ==========================================
    // Testy pro třídu ProductItem
    // ==========================================

    @Test
    public void testProductItemShortNameThrowsException() {
        // Název kratší než 3 znaky vyvolá ShortProductNameError
        assertThrows(ShortProductNameException.class, () -> new ProductItem("PC", 120, 5));
    }

    @Test
    public void testProductItemNegativeQuantityThrowsException() {
        // Záporné množství vyvolá NegativeValueError
        assertThrows(NegativeValueException.class, () -> new ProductItem("Myš počítačová", -5.0, 100));
    }

    @Test
    public void testProductItemNegativePriceThrowsException() {
        // Záporná cena vyvolá NegativeValueError
        assertThrows(NegativeValueException.class, () -> new ProductItem("Myš počítačová", 150.05, -50));
    }



    // ==========================================
    // Testy pro třídu Order
    // ==========================================

    @Test
    public void testOrderEmptyItemsListThrowsException() throws Exception {
        // Příprava validního zákazníka
        Customer validCustomer = new Customer("Jan Novák", "jan@novak.cz");

        // Pokud předáme prázdný seznam položek, očekáváme EmptyValueError
        assertThrows(EmptyValueException.class, () -> new Order(validCustomer, new ArrayList<>()));
    }
}