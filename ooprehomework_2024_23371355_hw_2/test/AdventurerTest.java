import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class AdventurerTest {
    Adventurer adventurer;

    @Before
    public void setUp() {
        adventurer = new Adventurer(12138,"jack");
    }

    @Test
    public void getId() {
        assertEquals(12138,adventurer.getId());
        //判断返回id是否正确
    }

    @Test
    public void getName() {
        assertEquals("jack",adventurer.getName());
        //判断返回姓名是否正确
    }

    @Test
    public void addBot() {
        int preBotNum = adventurer.getBotNum();
        Bottle bottle = new Bottle(1,"芍药壶",50);
        adventurer.addBot(bottle);
        assertEquals(preBotNum+1,adventurer.getBotNum());
    }

    @Test
    public void addEquip() {
        int preEquNum = adventurer.getEqNum();
        Equipment equipment = new Equipment(2,"宣花锤",62);
        adventurer.addEquip(equipment);
        assertEquals(preEquNum+1,adventurer.getEqNum());
    }

    @Test
    public void delBot() {
        adventurer.delBot(1);
        assertEquals(0,adventurer.getBotNum());
    }

    @Test
    public void delEqu() {
        adventurer.delEqu(2);
        assertEquals(0,adventurer.getEqNum());
    }

    @After
    public void tearDown() {
        System.out.println("pass Test!");
    }
}