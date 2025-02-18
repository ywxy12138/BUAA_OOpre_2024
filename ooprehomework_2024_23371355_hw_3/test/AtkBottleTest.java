import org.junit.Test;

import static org.junit.Assert.*;

public class AtkBottleTest {
     @Test
     public void getCe() {
         AtkBottle atkBottle = new AtkBottle(3,"6",20,"AtkBottle",20);
         assertEquals(20,atkBottle.getCe());
     }
}