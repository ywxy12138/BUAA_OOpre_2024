import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class BottleTest {
    Bottle bottle;

    @Before
    public void setUp() {
        bottle = new Bottle(1,"芍药壶",50);
    }

    @Test
    public void getId() {
        assertEquals(1,bottle.getId());
    }

    @Test
    public void getName() {
        assertEquals("芍药壶",bottle.getName());
    }

    @Test
    public void getCap() {
        assertEquals(50,bottle.getCap());
    }

    @After
    public void tearDown() {
        System.out.println("Congratulations！Pass Test!");
    }
}