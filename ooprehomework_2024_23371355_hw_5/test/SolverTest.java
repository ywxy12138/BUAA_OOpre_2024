import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

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
        list1.add("normal");
        list1.add("1");
        list1.add("3");
        Solver.operationTen(list1,adventurer);
        assertEquals(adventurer.getObject(2).getEffect(),20);
        assertEquals(adventurer2.getHitPoint(), 500);
        list1.set(4, "2");
        list1.add("2");
        Bottle bottle1 = Solver.opType("5","温酒","AtkBottle","20","50");
        adventurer.add(bottle1);
        Solver.operationSix(bottle1.getId(),adventurer);
        Solver.operationSeven(bottle1.getId(),adventurer);
        Solver.operationTen(list1,adventurer);
        assertEquals(19,adventurer.getObject(3).getEffect());
        assertEquals(adventurer1.getHitPoint(),447);
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

    @Test
    public void operationTen1() {
        Adventurer adventurer1 = new Adventurer(2,"吕布");
        Adventurer adventurer2 = new Adventurer(3,"刘焉");
        Adventurer adventurer3 = new Adventurer(4,"张鲁");
        Adventurer adventurer4 = new Adventurer(5,"刘璋");
        Adventurer adventurer5 = new Adventurer(6,"曹操");
        Adventurer adventurer6 = new Adventurer(7,"刘备");
        Adventurer adventurer7 = new Adventurer(8,"孙权");
        Adventurer adventurer8 = new Adventurer(9,"公孙瓒");
        Adventurer adventurer9 = new Adventurer(10,"袁绍");
        Adventurer adventurer10 = new Adventurer(11,"袁术");
        Adventurer adventurer11 = new Adventurer(12,"刘表");
        Adventurer adventurer12 = new Adventurer(13,"张角");
        Adventurer adventurer13 = new Adventurer(14,"严白虎");
        Adventurer adventurer14 = new Adventurer(15,"留赞");

        Solver.add(adventurer);
        Solver.add(adventurer1);
        Solver.add(adventurer2);
        Solver.add(adventurer3);
        Solver.add(adventurer4);
        Solver.add(adventurer5);
        Solver.add(adventurer6);
        Solver.add(adventurer7);
        Solver.add(adventurer8);
        Solver.add(adventurer9);
        Solver.add(adventurer10);
        Solver.add(adventurer11);
        Solver.add(adventurer12);
        Solver.add(adventurer13);
        Solver.add(adventurer14);
        //先把所有有关冒险者放入Solver类中的冒险者HashMap中

        Solver.operationEleven(2,adventurer);
        Solver.operationEleven(3,adventurer);
        Solver.operationEleven(4,adventurer1);
        Solver.operationEleven(5,adventurer1);
        Solver.operationEleven(6,adventurer2);
        Solver.operationEleven(7,adventurer4);
        Solver.operationEleven(8,adventurer5);
        Solver.operationEleven(9,adventurer5);
        Solver.operationEleven(10,adventurer6);
        Solver.operationEleven(11,adventurer7);
        Solver.operationEleven(12,adventurer9);
        Solver.operationEleven(13,adventurer9);
        Solver.operationEleven(13,adventurer10);
        Solver.operationEleven(14,adventurer10);
        Solver.operationEleven(15,adventurer10);

        Bottle bottle = Solver.opType("1","60","DefBottle","20","20");
        Bottle bottle1 = Solver.opType("2","60","DefBottle","20","20");
        Bottle bottle2 = Solver.opType("3","60","DefBottle","20","20");
        Bottle bottle3 = Solver.opType("4","60","DefBottle","20","20");

        Equipment equip1 = Solver.opEquip("5","雌雄双股剑","Sword","500","1000");
        Equipment equip2 = Solver.opEquip("6","青龙偃月刀","Blade","500","3000");
        Equipment equip3 = Solver.opEquip("7","丈八蛇矛","Axe","500","3000");

        adventurer11.add(bottle);
        adventurer11.add(bottle1);
        adventurer11.take(bottle);

        adventurer12.add(bottle2);
        adventurer12.add(bottle3);
        adventurer12.take(bottle3);
        //装药水瓶

        adventurer11.add(equip1);
        adventurer11.add(equip2);
        adventurer11.take(equip1);

        adventurer12.add(equip3);

        Equipment equipment = Solver.opEquip("8","贯石斧","Axe","50","10000");
        Equipment equipment1 = Solver.opEquip("9","七星刀", "Sword", "50","24");
        Equipment equipment2 = Solver.opEquip("10","诸葛连弩","Blade" ,"3", "1");
        Solver.operationThree(equipment,adventurer);
        Solver.operationSix(equipment.getId(),adventurer);
        Solver.operationThree(equipment1,adventurer);
        Solver.operationSix(equipment1.getId(),adventurer);
        Solver.operationThree(equipment2,adventurer);
        Solver.operationSix(equipment2.getId(),adventurer);

        ArrayList<String> list1 = new ArrayList<>();
        list1.add("10");
        list1.add("1");
        list1.add("贯石斧");
        list1.add("normal");
        list1.add("1");
        list1.add("10");
        Solver.operationTen(list1,adventurer);

        assertEquals(50,adventurer9.getHitPoint());
        assertTrue(adventurer11.getEquipbag().isEmpty());
        assertFalse(adventurer11.getBottleBag().isEmpty());
        assertEquals(adventurer9.getObjects().get(equip1.getId()).getName(),"雌雄双股剑");

        adventurer11.take(equip2);
        adventurer12.take(equip3);

        assertFalse(adventurer11.getEquipbag().isEmpty());
        assertFalse(adventurer12.getEquipbag().isEmpty());

        list1.set(2,"七星刀");
        Solver.operationTen(list1,adventurer);

        assertEquals(adventurer9.getHitPoint(),25);
        assertTrue(adventurer11.getEquipbag().isEmpty());
        assertTrue(adventurer12.getEquipbag().isEmpty());

        list1.set(2,"贯石斧");
        Solver.operationTen(list1,adventurer);

        int num = adventurer11.getHelpNums().get(adventurer9);
        assertEquals(num,3);

        assertEquals(adventurer9.getHitPoint(),2);

        list1.set(2,"诸葛连弩");
        Solver.operationTen(list1,adventurer);

        assertEquals(adventurer9.getHitPoint(),0);

        assertTrue(adventurer9.getWorkers().isEmpty());

        Solver.operationThree(equipment2,adventurer14);
        Solver.operationSix(equipment2.getId(),adventurer14);
        list1.set(3,"chain");
        list1.set(2,"诸葛连弩");
        list1.set(1,"15");
        list1.set(4,"2");
        list1.set(5,"6");
        list1.add("9");
        Solver.operationTen(list1,adventurer14);
        assertEquals(498,adventurer5.getHitPoint());
        assertEquals(498,adventurer7.getHitPoint());
        assertEquals(498,adventurer8.getHitPoint());
        assertEquals(498,adventurer10.getHitPoint());
        assertEquals(498,adventurer12.getHitPoint());
        assertEquals(498,adventurer13.getHitPoint());
        assertEquals(498,adventurer14.getHitPoint());

        list1.set(3,"normal");
        list1.set(4,"1");
        list1.set(5,"11");
        assertFalse(adventurer14.getObjects().isEmpty());
        Solver.operationTen(list1,adventurer14);
        assertEquals(496,adventurer10.getHitPoint());
        assertTrue(adventurer10.getObjects().isEmpty());
        assertTrue(adventurer14.getEquipbag().isEmpty());
        num = adventurer14.getHelpNums().get(adventurer10);
        assertEquals(0,num);
        assertTrue(adventurer14.getObjects().isEmpty());

        Equipment equipment3 = Solver.opEquip("15","诸葛连弩","Axe","1","20");
        Solver.operationThree(equipment3,adventurer14);
        Solver.operationSix(equipment3.getId(),adventurer14);
        Solver.operationTen(list1,adventurer14);
        assertTrue(adventurer14.getEquipbag().isEmpty());
        assertTrue(adventurer10.getObjects().isEmpty());
        assertEquals(49,adventurer10.getHitPoint());
        num = adventurer14.getHelpNums().get(adventurer10);
        assertEquals(1,num);
    }

    @Test
    public void chaFight() {
       Adventurer adventurer1 = new Adventurer(2,"吕布");
       Adventurer adventurer2 = new Adventurer(3,"刘焉");
       Adventurer adventurer3 = new Adventurer(4,"张鲁");
       Adventurer adventurer4 = new Adventurer(5,"刘璋");
       Adventurer adventurer5 = new Adventurer(6,"曹操");
       Adventurer adventurer6 = new Adventurer(7,"刘备");
       Adventurer adventurer7 = new Adventurer(8,"孙权");
       Adventurer adventurer8 = new Adventurer(9,"公孙瓒");
       Adventurer adventurer9 = new Adventurer(10,"袁绍");
       Adventurer adventurer10 = new Adventurer(11,"袁术");
       Adventurer adventurer11 = new Adventurer(12,"刘表");
       Adventurer adventurer12 = new Adventurer(13,"张角");
       Adventurer adventurer13 = new Adventurer(14,"严白虎");
       Adventurer adventurer14 = new Adventurer(15,"留赞");

       Solver.add(adventurer);
       Solver.add(adventurer1);
       Solver.add(adventurer2);
       Solver.add(adventurer3);
       Solver.add(adventurer4);
       Solver.add(adventurer5);
       Solver.add(adventurer6);
       Solver.add(adventurer7);
       Solver.add(adventurer8);
       Solver.add(adventurer9);
       Solver.add(adventurer10);
       Solver.add(adventurer11);
       Solver.add(adventurer12);
       Solver.add(adventurer13);
       Solver.add(adventurer14);
       //先把所有有关冒险者放入Solver类中的冒险者HashMap中

       Solver.operationEleven(2,adventurer);
       Solver.operationEleven(3,adventurer);
       Solver.operationEleven(4,adventurer1);
       Solver.operationEleven(5,adventurer1);
       Solver.operationEleven(6,adventurer2);
       Solver.operationEleven(7,adventurer4);
       Solver.operationEleven(8,adventurer5);
       Solver.operationEleven(9,adventurer5);
       Solver.operationEleven(10,adventurer6);
       Solver.operationEleven(11,adventurer7);
       Solver.operationEleven(12,adventurer9);
       Solver.operationEleven(13,adventurer9);
       Solver.operationEleven(13,adventurer10);
       Solver.operationEleven(14,adventurer10);
       Solver.operationEleven(15,adventurer10);

       ArrayList<Adventurer> list1 = new ArrayList<>();
       list1.add(adventurer);
       list1.add(adventurer7);

       adventurer3.setDef(30);
       assertEquals(adventurer3.getDef(),30);

       adventurer8.setDef(40);
       assertEquals(adventurer8.getDef(),40);

       adventurer11.setDef(30);
       adventurer12.setDef(40);
       adventurer13.setAtk(50);
       assertEquals(51,adventurer13.getAtk());

       adventurer14.setAtk(40);
       assertEquals(41,adventurer14.getAtk());

       assertEquals(40,Solver.chaFight(list1));


       assertFalse(list1.contains(adventurer11));

       assertTrue(list1.contains(adventurer12));
       assertTrue(list1.contains(adventurer13));
       assertTrue(list1.contains(adventurer14));
       assertTrue(list1.contains(adventurer8));
       assertTrue(list1.contains(adventurer10));
       assertTrue(list1.contains(adventurer1));
       assertTrue(list1.contains(adventurer2));
       assertTrue(list1.contains(adventurer3));
       assertTrue(list1.contains(adventurer4));
       assertTrue(list1.contains(adventurer5));
       assertTrue(list1.contains(adventurer6));
       assertTrue(list1.contains(adventurer7));
       assertTrue(list1.contains(adventurer9));
    }

    @Test
    public void operationEleven() {
        Adventurer adventurer1 = new Adventurer(2,"艾欧尼亚");
        Solver.add(adventurer1);
        Solver.operationEleven(2,adventurer);
        assertTrue(adventurer.getWorkers().contains(adventurer1));
    }

    @Test
    public void operationTwelve() {
        Adventurer adventurer1 = new Adventurer(2,"刘备");
        Adventurer adventurer2 = new Adventurer(3,"关羽");
        Adventurer adventurer3 = new Adventurer(4,"张飞");

        Solver.add(adventurer1);
        Solver.add(adventurer2);
        Solver.add(adventurer3);

        Solver.operationEleven(adventurer2.getId(),adventurer1);
        Solver.operationEleven(adventurer3.getId(),adventurer1);

        Equipment equip1 = new Equipment(5,"雌雄双股剑","Sword",500,1000);
        Equipment equip2 = new Equipment(6,"青龙偃月刀","Blade",500,3000);
        Equipment equip3 = new Equipment(7,"丈八蛇矛","Axe",500,3000);

        Solver.operationThree(equip1,adventurer1);
        Solver.operationThree(equip2,adventurer2);
        Solver.operationThree(equip3,adventurer3);

        assertEquals(3,adventurer1.getComCe(adventurer1));

        Solver.operationTwelve(adventurer1);
        //defeated,no monster die

        Solver.operationSix(equip1.getId(),adventurer1);

        Solver.operationTwelve(adventurer1);
        //defeated one monster

        assertEquals(40,adventurer1.getDef());
        assertEquals(40,adventurer2.getDef());
        assertEquals(40,adventurer3.getDef());

        Bottle bottle = Solver.opType("8","烈酒","AtkBottle","20","100");

        Solver.operationTwo(bottle,adventurer1);
        Solver.operationSix(bottle.getId(),adventurer1);
        //带上烈酒

        Solver.operationTwelve(adventurer1);

        assertEquals(80,adventurer1.getDef());
        assertEquals(80,adventurer2.getDef());
        assertEquals(80,adventurer3.getDef());

        assertEquals(1343,adventurer1.getComCe(adventurer1));

        Bottle bottle1 = Solver.opType("9","温酒","AtkBottle","20","1000");

        Solver.operationTwo(bottle1,adventurer1);
        Solver.operationSix(bottle1.getId(),adventurer1);
        //带上温酒

        Solver.operationTwelve(adventurer1);
        assertEquals(120,adventurer1.getDef());
        assertEquals(120,adventurer2.getDef());
        assertEquals(120,adventurer3.getDef());
        assertEquals(2583,adventurer1.getComCe(adventurer1));

        Equipment equip4 = new Equipment(10,"方天画戟","Sword",500,1000);
        Solver.operationThree(equip4,adventurer1);
        Solver.operationSix(equip4.getId(),adventurer1);

        Solver.operationTwelve(adventurer1);

        assertEquals(200,adventurer1.getDef());
        assertEquals(200,adventurer2.getDef());
        assertEquals(200,adventurer3.getDef());
        assertEquals(81,adventurer1.getAtk());
        assertEquals(81,adventurer2.getAtk());
        assertEquals(81,adventurer3.getAtk());
        assertEquals(3943,adventurer1.getComCe(adventurer1));

        Solver.operationTwelve(adventurer1);
        assertEquals(4393,adventurer1.getComCe(adventurer1));

        Solver.operationTwelve(adventurer1);
        assertEquals(4843,adventurer1.getComCe(adventurer1));

        Solver.operationTwelve(adventurer1);
        assertEquals(5443,adventurer1.getComCe(adventurer1));
    }
}