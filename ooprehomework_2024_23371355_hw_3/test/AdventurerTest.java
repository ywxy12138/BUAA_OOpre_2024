import org.junit.Test;

import static org.junit.Assert.*;

public class AdventurerTest {
    private Adventurer adventurer = new Adventurer(1,"德玛西亚");

    @Test
    public void getName() {
        assertEquals("德玛西亚",adventurer.getName());
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
    public void take() {
        OBject oBject = new Equipment(2,"如意金箍棒","Equipment",500,666);
        adventurer.take(oBject);
        assertTrue(adventurer.getBag().containsValue(oBject));
    }

    @Test
    public void enhDur() {
        Equipment oBject = new Equipment(2,"如意金箍棒","Equipment",500,666);
        adventurer.add(oBject);
        adventurer.enhDur(oBject.getId());
        assertEquals(501,adventurer.getObject(2).getEffect());
    }

    @Test
    public void getObject() {
        OBject oBject = new Equipment(2,"如意金箍棒","Equipment",500,666);
        adventurer.add(oBject);
        assertEquals(oBject,adventurer.getObject(2));
    }

    @Test
    public void isInBag() {
        OBject oBject = new Equipment(2,"如意金箍棒","Equipment",500,666);
        adventurer.take(oBject);
        assertTrue(adventurer.isInBag(2));
    }

    @Test
    public void del() {
        OBject oBject = new Bottle(3,"芍药壶","HpBottle",20,0);
        adventurer.add(oBject);
        adventurer.take(oBject);
        adventurer.del(oBject.getId(),0);
        assertFalse(adventurer.getObjects().containsValue(oBject));
    }

    @Test
    public void del1() {
        OBject oBject = new Bottle(3,"芍药壶","HpBottle",20,0);
        adventurer.add(oBject);
        adventurer.take(oBject);
        adventurer.del(oBject.getId(),1);
        assertFalse(adventurer.getObjects().containsValue(oBject));
    }

    @Test
    public void del2() {
        OBject oBject = new Equipment(2,"如意金箍棒","Equipment",500,666);
        adventurer.add(oBject);
        adventurer.take(oBject);
        adventurer.del(oBject.getId());
        assertFalse(adventurer.getObjects().containsValue(oBject));
    }

    @Test
    public void opType1() {
        HpBottle bottle = new HpBottle(5,"芍药壶",20,"HpBottle",0);
        assertEquals(bottle.getType(),adventurer.opType("5","芍药壶","HpBottle","20","0").getType());
    }

    @Test
    public void opType2() {
        AtkBottle bottle = new AtkBottle(5,"芍药壶",20,"AtkBottle",0);
        assertEquals(bottle.getType(),adventurer.opType("5","芍药壶","AtkBottle","20","0").getType());
    }

    @Test
    public void opType3() {
        DefBottle bottle = new DefBottle(5,"芍药壶",20,"DefBottle",0);
        assertEquals(bottle.getType(),"DefBottle");
    }

    @Test
    public void opEquip(){
        Equipment equipment = adventurer.opEquip("1","66","Equipment","20","20");
        assertEquals(equipment.getType(),"Equipment");
    }

    @Test
    public void enhAttr() {
        HpBottle bottle1 = new HpBottle(1,"60",20,"HpBottle",0);
        AtkBottle bottle2 = new AtkBottle(2,"60",20,"AtkBottle",1);
        DefBottle bottle3 = new DefBottle(3,"60",20,"DefBottle",4);

        adventurer.enhAttr(bottle1);
        assertEquals(520,adventurer.getHitPoint());
        adventurer.enhAttr(bottle2);
        assertEquals(2,adventurer.getAtk());
        adventurer.enhAttr(bottle3);
        assertEquals(4,adventurer.getDef());
    }

    @Test
    public void operationTwo() {
        Bottle bottle = adventurer.opType("1","60","HpBottle","20","0");
        adventurer.operationTwo(bottle,adventurer);
        adventurer.add(bottle);
        assertTrue(adventurer.getObjects().containsValue(bottle));
    }

    @Test
    public void operationThree() {
        Equipment equipment = new Equipment(2,"60","Equipment",20,0);
        adventurer.operationThree(equipment,adventurer);
        adventurer.add(equipment);
        assertTrue(adventurer.getObjects().containsValue(equipment));
    }

    @Test
    public void operationFour() {
        Equipment equipment = new Equipment(2,"60","Equipment",20,0);
        adventurer.operationThree(equipment,adventurer);
        adventurer.operationFour(2,adventurer);
        assertEquals(21,adventurer.getObject(2).getEffect());
    }

    @Test
    public void operationFive() {
        Bottle bottle = new Bottle(1,"60","HpBottle",20,0);
        Equipment equipment = new Equipment(2,"60","Equipment",20,0);
        adventurer.operationThree(equipment,adventurer);
        adventurer.operationTwo(bottle,adventurer);
        adventurer.operationFive(2,adventurer);
        assertFalse(adventurer.getObjects().containsValue(equipment));
        adventurer.operationFive(1,adventurer);
        assertFalse(adventurer.getObjects().containsValue(bottle));
    }

    @Test
    public void operationSix() {
        Bottle bottle = adventurer.opType("1","60","HpBottle","20","0");
        adventurer.add(bottle);
        adventurer.take(bottle);
        Equipment equipment = adventurer.opEquip("2","60","Equipment","20","0");
        adventurer.add(equipment);
        adventurer.operationSix(1,adventurer);
        adventurer.operationSix(2,adventurer);
        adventurer.add(bottle);
        adventurer.take(bottle);
        assertTrue(adventurer.getObjects().containsValue(equipment));
        assertTrue(adventurer.getObjects().containsValue(bottle));
    }

    @Test
    public void operationSeven() {
        Bottle bottle = adventurer.opType("1","60","HpBottle","20","0");
        adventurer.add(bottle);
        adventurer.operationSeven(bottle.getId(), adventurer);
        assertEquals(bottle.getIsEmpty(),0);
        assertFalse(adventurer.getBag().containsValue(bottle));
        adventurer.take(bottle);
        adventurer.operationSeven(bottle.getId(),adventurer);
        assertTrue(adventurer.getBag().containsValue(bottle));
        assertEquals(bottle.getIsEmpty(),1);
        adventurer.operationSeven(bottle.getId(), adventurer);
        assertFalse(adventurer.getBag().containsValue(bottle));
        assertFalse(adventurer.getObjects().containsValue(bottle));
    }
}