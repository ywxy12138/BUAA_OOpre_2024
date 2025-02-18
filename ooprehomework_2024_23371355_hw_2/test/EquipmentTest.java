import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class EquipmentTest {
    Equipment equipment;

    @Before
    public void setUp() {
        equipment = new Equipment(2,"宣花锤",62);
    }

    @Test
    public void getId() {
        assertEquals(2,equipment.getId());
    }

    @Test
    public void getName() {
        assertEquals("宣花锤",equipment.getName());
    }

    @Test
    public void getDur() {
        assertEquals(62,equipment.getDur());
    }

    @Test
    public void enhDur() {
        equipment.enhDur();
        assertEquals(63,equipment.getDur());
    }

    @After
    public void tearDown() {
        System.out.println("pass Test!");
    }
}