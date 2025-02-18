import org.junit.Test;

import static org.junit.Assert.*;

public class AtkBottleTest {

    @Test
    public void getCe() {
        AtkBottle bottle = new AtkBottle(1,"芍药壶",20,"AtkBottle",20);
        assertEquals(bottle.getCe(),20);
    }
}