import org.junit.Test;

import static org.junit.Assert.*;

public class BladeTest {

    @Test
    public void getHipDamage() {
        Blade blade = new Blade(1,"七星刀","Blade",20,30);
        assertEquals(60,blade.getHipDamage(30));
    }
}