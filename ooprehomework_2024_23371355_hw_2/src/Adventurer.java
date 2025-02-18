import java.util.ArrayList;//引入ArrayList类

public class Adventurer {
    private final int id;//ID
    private final String name;//名字
    private ArrayList<Bottle> bottles = new ArrayList<>();//药水瓶数组存储冒险者拥有的药水瓶
    private ArrayList<Equipment> equipments = new ArrayList<>();//装备数组

    public Adventurer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBotNum() {
        return bottles.size();
    }

    public int getEqNum() {
        return equipments.size();
    }

    public void addBot(Bottle bottle) {
        bottles.add(bottle);
    }

    public void addEquip(Equipment equipment) {
        equipments.add(equipment);
    }

    public void enhDur(int equId) {
        for (Equipment equipment : equipments) {
            if (equipment.getId() == equId) {
                equipment.enhDur();
                break;
            }
        }
    }

    public void delBot(int botId) {
        for (Bottle bot : bottles) {
            if (bot.getId() == botId) {
                System.out.printf("%d %s %d\n",bottles.size() - 1,bot.getName(),bot.getCap());
                bottles.remove(bot);
                break;
            }
        }
    }

    public void delEqu(int equId) {
        for (Equipment equ : equipments) {
            if (equ.getId() == equId) {
                System.out.printf("%d %s %d\n",equipments.size() - 1,equ.getName(),equ.getDur());
                equipments.remove(equ);
                break;
            }
        }
    }
}
