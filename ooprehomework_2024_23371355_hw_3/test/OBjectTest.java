import org.junit.Test;

import static org.junit.Assert.*;

public class OBjectTest {
    OBject obj = new OBject(1,"如意金箍棒","Equipment",20,2);

    @Test
    public void getId() {
        assertEquals(obj.getId(),1);
    }

    @Test
    public void getName() {
        assertEquals(obj.getName(),"如意金箍棒");
    }

    @Test
    public void getType() {
        assertEquals(obj.getType(),"Equipment");
    }

    @Test
    public void getEffect() {
        assertEquals(obj.getEffect(),20);
    }

    @Test
    public void getCe() {
        assertEquals(obj.getCe(),2);
    }
}