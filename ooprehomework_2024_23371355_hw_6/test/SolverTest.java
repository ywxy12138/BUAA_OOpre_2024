import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SolverTest {
    private Adventurer adventurer = new Adventurer(1,"德玛西亚");

    @Test
    public void opType1() {
        HpBottle bottle = new HpBottle(5,"芍药壶",20,"HpBottle",0);
        assertEquals(bottle.getType(), Solver.opType("5","芍药壶","HpBottle","20","0").getType());
    }

    @Test
    public void opType2() {
        AtkBottle bottle = new AtkBottle(5,"芍药壶",20,"AtkBottle",0);
        assertEquals(bottle.getType(), Solver.opType("5","芍药壶","AtkBottle","20","0").getType());
    }

    @Test
    public void opType3() {
        DefBottle bottle = new DefBottle(5,"芍药壶",20,"DefBottle",0);
        assertEquals(bottle.getType(),"DefBottle");
    }

    @Test
    public void opEquip1(){
        Equipment equipment = Solver.opEquip("1","66","Axe","20","20");
        assertEquals(equipment.getType(),"Axe");
    }

    @Test
    public void opEquip2(){
        Equipment equipment = Solver.opEquip("1","66","Sword","20","20");
        assertEquals(equipment.getType(),"Sword");
    }

    @Test
    public void opEquip3(){
        Equipment equipment = Solver.opEquip("1","66","Blade","20","20");
        assertEquals(equipment.getType(),"Blade");
    }

    @Test
    public void opFrag() {
        Bottle bottle = new Bottle(1,"fs","HpBottle",20,0);
        Equipment equipment = Solver.opEquip("2","66","Axe","20","20");
        Fragment fragment0 = new Fragment(0,"dff");
        Fragment fragment1 = new Fragment(1,"dff");
        Fragment fragment2 = new Fragment(2,"dff");
        Fragment fragment3 = new Fragment(3,"dff");
        Fragment fragment4 = new Fragment(4,"dff");
        Fragment fragment5 = new Fragment(5,"dff");

        Solver.operationEight(fragment0,adventurer);
        Solver.operationEight(fragment1,adventurer);
        Solver.operationEight(fragment2,adventurer);
        Solver.operationEight(fragment3,adventurer);
        Solver.operationEight(fragment4,adventurer);
        Solver.operationEight(fragment5,adventurer);
        Solver.opFrag("dffi",1,adventurer);
        assertEquals(6,adventurer.getFrag().size());
        Solver.operationTwo(bottle,adventurer);
        bottle.setIsEmpty();
        assertEquals(bottle.getIsEmpty(),1);
        Solver.opFrag("dff",1,adventurer);
        assertEquals(1,adventurer.getFrag().size());
        assertEquals(bottle.getIsEmpty(),0);
        assertFalse(adventurer.getFrag().containsValue(fragment0));
        assertFalse(adventurer.getFrag().containsValue(fragment1));
        assertFalse(adventurer.getFrag().containsValue(fragment2));
        assertFalse(adventurer.getFrag().containsValue(fragment3));
        assertFalse(adventurer.getFrag().containsValue(fragment4));
        assertTrue(adventurer.getFrag().containsValue(fragment5));
        Solver.operationEight(fragment0,adventurer);
        Solver.operationEight(fragment1,adventurer);
        Solver.operationEight(fragment2,adventurer);
        Solver.operationEight(fragment3,adventurer);
        Solver.operationEight(fragment4,adventurer);
        Solver.operationThree(equipment,adventurer);
        Solver.opFrag("dff",2,adventurer);
        assertEquals(21,equipment.getEffect());
        Solver.operationEight(fragment0,adventurer);
        Solver.operationEight(fragment1,adventurer);
        Solver.operationEight(fragment2,adventurer);
        Solver.operationEight(fragment3,adventurer);
        Solver.operationEight(fragment4,adventurer);
        Solver.opFrag("dff",3,adventurer);
        assertEquals("dff",adventurer.getObject(3).getName());
        assertEquals(100,adventurer.getObject(3).getEffect());
        assertEquals("HpBottle",adventurer.getObject(3).getType());
        assertEquals(100,adventurer.getObject(3).getCe());
        Solver.opFrag("dff",1,adventurer);
        assertEquals(1,adventurer.getFrag().size());
    }

    @Test
    public void operationTwo() {
        Bottle bottle = Solver.opType("1","60","HpBottle","20","0");
        Solver.operationTwo(bottle,adventurer);
        adventurer.add(bottle);
        assertTrue(adventurer.getObjects().containsValue(bottle));
    }

    @Test
    public void operationThree() {
        Equipment equipment = new Equipment(2,"60","Equipment",20,0);
        Solver.operationThree(equipment,adventurer);
        adventurer.add(equipment);
        assertTrue(adventurer.getObjects().containsValue(equipment));
    }

    @Test
    public void operationFour() {
        Equipment equipment = new Equipment(2,"60","Equipment",20,0);
        Solver.operationThree(equipment,adventurer);
        Solver.operationFour(2,adventurer);
        assertEquals(21,adventurer.getObject(2).getEffect());
    }

    @Test
    public void operationFive() {
        Bottle bottle = new Bottle(1,"60","HpBottle",20,0);
        Equipment equipment = new Equipment(2,"60","Equipment",20,0);
        Solver.operationThree(equipment,adventurer);
        Solver.operationTwo(bottle,adventurer);
        Solver.operationFive(2,adventurer);
        assertFalse(adventurer.getObjects().containsValue(equipment));
        Solver.operationFive(1,adventurer);
        assertFalse(adventurer.getObjects().containsValue(bottle));
    }

    @Test
    public void operationSix() {
        Bottle bottle = Solver.opType("1","60","HpBottle","20","0");
        adventurer.add(bottle);
        adventurer.take(bottle);
        Equipment equipment = Solver.opEquip("2","60","Axe","20","0");
        adventurer.add(equipment);
        Solver.operationSix(1,adventurer);
        Solver.operationSix(2,adventurer);
        adventurer.add(bottle);
        adventurer.take(bottle);
        assertTrue(adventurer.getObjects().containsValue(equipment));
        assertTrue(adventurer.getObjects().containsValue(bottle));
    }

    @Test
    public void operationSeven() {
        Bottle bottle = Solver.opType("1","60","HpBottle","20","0");
        adventurer.add(bottle);
        Solver.operationSeven(bottle.getId(), adventurer);
        assertEquals(bottle.getIsEmpty(),0);
        assertFalse(adventurer.getBottleBag().containsValue(bottle));
        adventurer.take(bottle);
        Solver.operationSeven(bottle.getId(),adventurer);
        assertTrue(adventurer.getBottleBag().containsValue(bottle));
        assertEquals(bottle.getIsEmpty(),1);
        Solver.operationSeven(bottle.getId(), adventurer);
        assertFalse(adventurer.getBottleBag().containsValue(bottle));
        assertFalse(adventurer.getObjects().containsValue(bottle));
    }

    @Test
    public void operationEight() {
        Fragment fragment = new Fragment(1,"46");
        Solver.operationEight(fragment,adventurer);
        assertTrue(adventurer.getFrag().containsValue(fragment));
    }

    @Test
    public void operationTen() {
        Adventurer adventurer1 = new Adventurer(2,"张绣");//盘踞宛城，观乱世之诡谲。
        Adventurer adventurer2 = new Adventurer(3,"刘焉");//我怎有图谋不轨之心

        Solver.add(adventurer);
        Solver.add(adventurer2);
        Solver.add(adventurer1);

        Bottle bottle = Solver.opType("1","60","DefBottle","20","20");
        adventurer2.add(bottle);
        adventurer2.take(bottle);
        Solver.operationSeven(bottle.getId(),adventurer2);
        Equipment equipment1 = Solver.opEquip("2","贯石斧","Axe","20","3");
        Equipment equipment2 = Solver.opEquip("3","寒冰剑","Sword","20","2");
        Equipment equipment3 = Solver.opEquip("4","青龙偃月刀","Blade","20","3");
        adventurer.add(equipment1);
        adventurer.add(equipment2);
        adventurer.add(equipment3);
        Solver.operationSix(equipment1.getId(),adventurer);
        Solver.operationSix(equipment2.getId(),adventurer);
        Solver.operationSix(equipment3.getId(),adventurer);
        Solver.operationSeven(bottle.getId(),adventurer2);
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("10");
        list1.add("1");
        list1.add("寒冰剑");
        list1.add("1");
        list1.add("3");
        Solver.operationTen(list1,adventurer);
        assertEquals(adventurer.getObject(2).getEffect(),20);
        assertEquals(adventurer2.getHitPoint(), 500);
        list1.set(3, "2");
        list1.add("2");
        Bottle bottle1 = Solver.opType("5","温酒","AtkBottle","20","50");
        adventurer.add(bottle1);
        Solver.operationSix(bottle1.getId(),adventurer);
        Solver.operationSeven(bottle1.getId(),adventurer);
        Solver.operationTen(list1,adventurer);
        assertEquals(19,adventurer.getObject(3).getEffect());
        assertEquals(adventurer1.getHitPoint(), 447);
        assertEquals(adventurer2.getHitPoint(),467);
        list1.set(2,"青龙偃月刀");
        Solver.operationTen(list1,adventurer);
        assertEquals(19,adventurer.getObject(4).getEffect());
        assertEquals(adventurer1.getHitPoint(),393);
        assertEquals(adventurer2.getHitPoint(),413);
        list1.set(2,"贯石斧");
        Solver.operationTen(list1,adventurer);
        assertEquals(19,adventurer.getObject(2).getEffect());
        assertEquals(adventurer1.getHitPoint(),39);
        assertEquals(adventurer2.getHitPoint(),41);
    }
}