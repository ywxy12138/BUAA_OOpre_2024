import org.junit.Test;

import static org.junit.Assert.*;

public class HpBottleTest {

    @Test
    public void getCe() {
        HpBottle hpBottle = new HpBottle(2,"6",20,"HpBottle",0);
        assertEquals(20,hpBottle.getCe());
    }
}