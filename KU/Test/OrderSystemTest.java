import cz.osu.model.Customer;
import cz.osu.model.Order;
import cz.osu.model.ProductItem;
import cz.osu.service.OrderSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class OrderSystemTest {

    private OrderSystem system;

    @BeforeEach
    public void setUp() throws Exception {
        system = new OrderSystem();

        Customer c1 = new Customer("Jan Novák", "jan@novak.cz");
        Customer c2 = new Customer("Petr Svoboda", "petr@svoboda.com");

        // Objednávka 1 (Celkem: 2*100 + 1*50 = 250)
        ProductItem i1 = new ProductItem("Myš počítačová", 100.0, 10);
        ProductItem i2 = new ProductItem("Podložka", 250.99, 2);
        Order o1 = new Order(c1, Arrays.asList(i1, i2));

        // Objednávka 2 (Celkem: 1*100 + 4*50 = 300)
        ProductItem i3 = new ProductItem("Myš počítačová", 1000, 5);
        ProductItem i4 = new ProductItem("Podložka", 40.99, 4);
        Order o2 = new Order(c2, Arrays.asList(i3, i4));

        system.addOrder(o1);
        system.addOrder(o2);
    }



    @Test
    public void testTotalRevenue() {
        assertEquals(6665.94, system.getTotalRevenue(), 0.001);
    }

    @Test
    public void testAverageOrderValue() {
        assertEquals(3332.97, system.getAverageOrderValue(), 0.001);
    }

    @Test
    public void testMostSoldProduct() {
        // Myš: 3 kusy, Podložka: 5 kusů -> Podložka je nejprodávanější
        assertEquals("Myš počítačová", system.getMostSoldProduct());
    }
}