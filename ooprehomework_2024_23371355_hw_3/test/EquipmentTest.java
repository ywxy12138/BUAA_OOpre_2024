import org.junit.Test;

import static org.junit.Assert.*;

public class EquipmentTest {
    Equipment equipment = new Equipment(2,"如意金箍棒","Equipment",50,666);

    @Test
    public void getCE() {
        assertEquals(666,equipment.getCE());
    }

    @Test
    public void enhDur() {
        equipment.enhDur();
        assertEquals(51,equipment.getEffect());
    }

    @Test
    public void getEffect() {
        assertEquals(50,equipment.getEffect());
    }
}