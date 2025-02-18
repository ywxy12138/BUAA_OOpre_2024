import org.junit.Test;

import static org.junit.Assert.*;

public class DefBottleTest {

    @Test
    public void getCe() {
        DefBottle defBottle = new DefBottle(1,"6",20,"DefBottle",2);
        assertEquals(2,defBottle.getCe());
    }
}