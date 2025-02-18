import org.junit.Test;

import static org.junit.Assert.*;

public class AdventurerTest {
    Adventurer adventurer = new Adventurer(1,"曹髦");

    @Test
    public void getId() {
        assertEquals(1,adventurer.getId());
    }

    @Test
    public void getName() {
        assertEquals("曹髦",adventurer.getName());
    }

    @Test
    public void getCE() {
        assertEquals(1,adventurer.getCE());
    }

    @Test
    public void getHitPoint() {
        assertEquals(500,adventurer.getHitPoint());
    }

    @Test
    public void getAtk() {
        assertEquals(1,adventurer.getAtk());
    }

    @Test
    public void getDef() {
        assertEquals(0,adventurer.getDef());
    }

    @Test
    public void add() {
        OBject oBject = new Equipment(2,"如意金箍棒","Equipment",500,666);
        adventurer.add(oBject);
        assertTrue(adventurer.getObjects().containsValue(oBject));
    }

    @Test
    public void setHitPoint() {
        adventurer.setHitPoint(1,0);
        assertEquals(adventurer.getHitPoint(),50);
        adventurer.setHitPoint(2,20);
        assertEquals(adventurer.getHitPoint(),30);
    }

    @Test
    public void getObjects() {
        Bottle bottle = new Bottle(1,"dsj","HpBottle",20,30);
        adventurer.add(bottle);
        assertEquals(adventurer.getObjects().get(1),bottle);
    }

    @Test
    public void getBottleBag() {
        Bottle bottle = new Bottle(1,"dsj","HpBottle",20,30);
        adventurer.add(bottle);
        adventurer.take(bottle);
        assertEquals(adventurer.getBottleBag().get(1),bottle);
    }

    @Test
    public void take() {
        Equipment equipment1 = new Equipment(1,"dsj","Axe",20,30);
        Equipment equipment2 = new Equipment(2,"dsj","Axe",20,40);
        adventurer.add(equipment1);
        adventurer.add(equipment2);
        adventurer.take(equipment1);
        assertEquals(adventurer.getEquipbag().get(1),equipment1);
        adventurer.take(equipment2);
        assertEquals(adventurer.getEquipbag().get(2),equipment2);
        assertNull(adventurer.getEquipbag().get(1));
        Bottle bottle = new Bottle(3,"dsj","HpBottle",20,0);
        Bottle bottle1 = new Bottle(4,"dsj","HpBottle",30,0);
        adventurer.add(bottle);
        adventurer.add(bottle1);
        adventurer.take(bottle);
        assertEquals(adventurer.getBottleBag().get(3),bottle);
        adventurer.take(bottle1);
        assertEquals(adventurer.getBottleBag().get(3),bottle);
        assertNull(adventurer.getBottleBag().get(4));
    }

    @Test
    public void isInBag() {
        Equipment equipment1 = new Equipment(1,"dsj","Axe",20,30);
        Bottle bottle = new Bottle(3,"dsj","HpBottle",20,0);
        adventurer.add(bottle);
        adventurer.take(bottle);
        adventurer.add(equipment1);
        adventurer.take(equipment1);
        assertTrue(adventurer.isInBag(bottle));
        assertTrue(adventurer.isInBag(equipment1));
    }

    @Test
    public void del() {
    }

    @Test
    public void testDel() {
    }

    @Test
    public void testDel1() {
    }

    @Test
    public void enhAttr() {
    }

    @Test
    public void success() {
    }

    @Test
    public void testSuccess() {
    }

    @Test
    public void testSuccess1() {
    }

    @Test
    public void fail() {
    }

    @Test
    public void testFail() {
    }

    @Test
    public void fightFail() {
    }
}