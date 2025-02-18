import org.junit.Test;

import static org.junit.Assert.*;

public class BottleTest {
    Bottle bottle = new Bottle(2,"芍药壶","HpBottle",50,20);
    @Test
    public void getIsEmpty() {
        assertEquals(0,bottle.getIsEmpty());
    }

    @Test
    public void getCE() {
        assertEquals(20,bottle.getCE());
    }

    @Test
    public void setIsEmpty() {
        bottle.setIsEmpty();
        assertEquals(1,bottle.getIsEmpty());
    }
}