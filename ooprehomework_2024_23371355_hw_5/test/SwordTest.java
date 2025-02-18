import org.junit.Test;

import static org.junit.Assert.*;

public class SwordTest {

    @Test
    public void getHipDamage() {
        Sword sword = new Sword(1,"雌雄双股剑","Sword",20,60);
        int hipDamage = sword.getHipDamage(20,20);
        assertEquals(60,hipDamage);
    }
}