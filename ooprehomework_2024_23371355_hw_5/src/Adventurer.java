import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Adventurer implements CE,Adventurerloader {
    private final int id;//ID
    private final String name;//名字
    private int hitPoint;//生命力
    private int atk;//攻击力
    private int def;//防御力
    private HashMap<Integer,Equipment> equipbag = new HashMap<>();//存储装备得背包
    private HashMap<Integer,Bottle> bottlebag = new HashMap<>();//存储药水瓶得背包
    private HashMap<Integer,OBject> objects = new HashMap<>();//物品hashmap
    private HashMap<Integer,Fragment> fragments = new HashMap<>();//存储碎片的容器，为了同物品区分开，另用一个容器装
    private ArrayList<Adventurer> workers = new ArrayList<>();
    private HashMap<Adventurer,Integer> helpNums = new HashMap<>();

    public Adventurer(int id, String name) {
        this.id = id;
        this.name = name;
        this.hitPoint = 500;
        this.atk = 1;
        this.def = 0;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void addHelp(Adventurer adventurer) {
        int helpNum = helpNums.get(adventurer);
        helpNum++;
        helpNums.replace(adventurer, helpNum);
    }

    @Override
    public  int getCE() {
        return atk + def;
    }

    public int getComCe(Adventurer adventurer) {
        int totalComCe = adventurer.getCE();
        for (Equipment equipment: adventurer.getEquipbag().values()) {
            totalComCe += equipment.getCE();
        }
        for (Bottle bottle: adventurer.getBottleBag().values()) {
            totalComCe += bottle.getCE();
        }
        for (Adventurer worker: adventurer.getWorkers()) {
            totalComCe += worker.getCE();
        }
        return totalComCe;
    }

    @Override
    public void addWorker(Adventurer adventurer) {
        workers.add(adventurer);
        adventurer.getHelpNums().put(this,0);
    }

    public void needHelp(Adventurer adventurer) {
        for (Adventurer worker : adventurer.getWorkers()) {
            if (!worker.getEquipbag().isEmpty()) {
                for (Equipment equip : worker.getEquipbag().values()) {
                    adventurer.getObjects().put(equip.getId(), equip);
                    worker.getObjects().remove(equip.getId());
                }
                worker.getEquipbag().clear();
            }
            worker.addHelp(adventurer);
        }
    }

    public void judge() {
        Iterator<Adventurer> iter = workers.iterator();
        while (iter.hasNext()) {
            Adventurer adventurer = iter.next();
            if (adventurer.getHelpNums().get(this) > 3) {
                adventurer.getHelpNums().remove(this); //remove the relationship between boss
                iter.remove();
            }
        }
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int type, int hipDamage) {
        if (type == 1) {
            this.hitPoint /= 10;
        }
        else {
            this.hitPoint -= hipDamage;
        }
    }

    public void setAtk(int enhAtk) {
        this.atk += enhAtk;
    }

    public void setDef(int enhDef) {
        this.def += enhDef;
    }

    public HashMap<Integer,OBject> getObjects() {
        return objects;
    }

    public HashMap<Integer,Bottle> getBottleBag() {
        return bottlebag;
    }

    public HashMap<Integer,Equipment> getEquipbag() {
        return equipbag;
    }

    public HashMap<Integer,Fragment> getFrag() {
        return fragments;
    }

    public HashMap<Integer,Fragment> getFragments() { return fragments; }

    public ArrayList<Adventurer> getWorkers() {
        return workers;
    }

    public HashMap<Adventurer,Integer> getHelpNums() {
        return helpNums;
    }

    public void add(OBject obj) {
        objects.put(obj.getId(), obj);
    }

    public void add(Fragment obj) { fragments.put(obj.getId(), obj); }

    public void take(OBject obj) {
        if (obj instanceof Equipment) {
            Equipment equip = (Equipment) obj;
            Equipment equipment = null;
            for (Map.Entry<Integer, Equipment> entry : equipbag.entrySet()) {
                if (equip.getName().equals(entry.getValue().getName())) {
                    equipment = entry.getValue();
                    break;
                }
            }
            if (equipment != null) {
                equipbag.remove(equipment.getId());
            }
            equipbag.put(equip.getId(), equip);
        }
        if (obj instanceof Bottle) {
            Bottle bottle = (Bottle) obj;
            int cnt = 0;
            int maxBottles = (atk + def) / 5 + 1;
            for (Map.Entry<Integer, Bottle> entry : bottlebag.entrySet()) {
                if (bottle.getName().equals(entry.getValue().getName())) {
                    cnt++;
                }
            }
            if (cnt < maxBottles) {
                bottlebag.put(bottle.getId(), bottle);
            }
        }
    }

    public void enhDur(int equId) {
        OBject equip = objects.get(equId);
        if (equip instanceof Equipment) {
            Equipment holdEquip = (Equipment) equip;
            holdEquip.enhDur();
        }
    }

    public OBject getObject(int id) {
        return objects.get(id);
    }

    public boolean isInBag(OBject object) {
        if (object instanceof Equipment) {
            return equipbag.containsKey(object.getId());
        }
        else {
            return bottlebag.containsKey(object.getId());
        }
    }

    public void del(int botId, int flag) {
        OBject bottle = objects.get(botId);
        if (bottle instanceof Bottle) {
            Bottle bot = (Bottle) bottle;
            if (flag == 1) {
                System.out.printf("%s %s %d\n", bot.getType(), bot.getName(), bot.getEffect());
            }
        }
        objects.remove(botId);
        bottlebag.remove(botId);
    }

    public void del(int equId) {
        OBject equip = objects.get(equId);
        if (equip instanceof Equipment) {
            Equipment equ = (Equipment) equip;
            System.out.printf("%s %s %d\n",equ.getType(),equ.getName(), equ.getEffect());
        }
        objects.remove(equId);
        equipbag.remove(equId);
    }

    public void del(ArrayList<Integer> list) {
        for (int i = 0; i < 5; i++) {
            fragments.remove(list.get(i));
        }
    }

    public void enhAttr(Bottle bottle) {
        switch (bottle.getType()) {
            case "HpBottle": {
                if (bottle instanceof HpBottle) {
                    HpBottle hpBottle = (HpBottle) bottle;
                    hitPoint += hpBottle.getCe();
                }
                break;
            }
            case "AtkBottle": {
                if (bottle instanceof AtkBottle) {
                    AtkBottle atkBottle = (AtkBottle) bottle;
                    atk += atkBottle.getCe();
                }
                break;
            }
            case "DefBottle": {
                if (bottle instanceof DefBottle) {
                    DefBottle defBottle = (DefBottle) bottle;
                    def += defBottle.getCe();
                }
                break;
            }
            default: {
                break;
            }
        }
    }

    public void success() {
        System.out.printf("%s %d %d %d\n",name,hitPoint,atk,def);
    }

    public void success(char c, int id, String fragName, Adventurer markedAd) {
        switch (c) {
            case 'a': {
                Bottle bottle = (Bottle) markedAd.getObject(id);
                if (bottle.getIsEmpty() == 1) {
                    bottle.setIsEmpty();
                }
                System.out.printf("%s %d\n",bottle.getName(), bottle.getEffect());
                break;
            }
            case 'b': {
                markedAd.enhDur(id);
                break;
            }
            case 'c': {
                Bottle bottle = new HpBottle(id, fragName, 100,"HpBottle", 0);
                markedAd.add(bottle);
                System.out.printf("Congratulations! HpBottle %s acquired\n", fragName);
                break;
            }
            default: {
                break;
            }
        }
    }

    public void success(String type, Equipment equip, ArrayList<Adventurer> list, Adventurer ad) {
        int ty = 0;
        int hipDamage = 0;

        switch (equip.getType()) {
            case "Axe": {
                ty = 1;
                break;
            }
            case "Sword": {
                ty = 2;
                break;
            }
            case "Blade": {
                ty = 3;
                Blade blade = (Blade) equip;
                hipDamage = blade.getHipDamage(ad.getAtk());
                break;
            }
            default: {
                break;
            }
        }
        HashMap<Adventurer,Integer> befHip = new HashMap<>();
        for (Adventurer adventurer : list) {
            if (!befHip.containsKey(adventurer)) {
                befHip.put(adventurer, adventurer.getHitPoint());
            }
            if (ty == 2) {
                Sword sword = (Sword) equip;
                hipDamage = sword.getHipDamage(ad.getAtk(),adventurer.getDef());
            }
            adventurer.setHitPoint(ty,hipDamage);
        }
        //record the hip before fight and after
        if (equip.useEquip() == 0) {
            ad.getEquipbag().remove(equip.getId());
            ad.getObjects().remove(equip.getId());
        }
        //change the durability of the equipment
        if (type.equalsIgnoreCase("chain")) {
            System.out.printf("%d\n", chaSuc(befHip));
        } else {
            norSuc(list,befHip);
        }
    }

    public static void norSuc(ArrayList<Adventurer> list,HashMap<Adventurer,Integer> bef) {
        for (Adventurer adventurer : list) {
            if (adventurer.getHitPoint() <= (bef.get(adventurer) / 2)) {
                adventurer.needHelp(adventurer);
                adventurer.judge();
            }
            System.out.printf("%s %d\n",adventurer.getName(),adventurer.getHitPoint());
        }
        //不能直接在这里输出，这样的输出并不符合顺序的要求，要在原有的数组中顺序输出
    }

    public static int chaSuc(HashMap<Adventurer,Integer> bef) {
        int tot = 0;
        for (Adventurer adventurer : bef.keySet()) {
            tot += (bef.get(adventurer) - adventurer.getHitPoint());
        }
        return tot;
    }

    public void fail(String bottleName) {
        System.out.printf("%s fail to use %s\n",name,bottleName);
    }

    public void fail(int num) {
        System.out.printf("%d: Not enough fragments collected yet\n",num);
    }

    public void fightFail(int id) {
        System.out.printf("Adventurer %d defeated\n",id);
    }
}
