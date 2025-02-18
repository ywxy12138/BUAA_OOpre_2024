import java.util.HashMap;//导入hashmap容器

public class Adventurer implements CE {
    private final int id;//ID
    private final String name;//名字
    private int hitPoint;//体力
    private int atk;//攻击力
    private int def;//防御力
    private HashMap<Integer,OBject> bag = new HashMap<>();//药水瓶hashmap存储冒险者拥有的药水瓶
    private HashMap<Integer,OBject> objects = new HashMap<>();//装备hashmap

    public Adventurer(int id, String name) {
        this.id = id;
        this.name = name;
        this.hitPoint = 500;
        this.atk = 1;
        this.def = 0;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getCE() {
        return atk + def;
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

    public HashMap<Integer,OBject> getBag() {
        return bag;
    }

    public HashMap<Integer,OBject> getObjects() {
        return objects;
    }

    public void add(OBject obj) {
        objects.put(obj.getId(), obj);
    }

    public void take(OBject obj) {
        bag.put(obj.getId(), obj);
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

    public boolean isInBag(int id) {
        return bag.containsKey(id);
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
        bag.remove(botId);
    }

    public void del(int equId) {
        OBject equip = objects.get(equId);
        if (equip instanceof Equipment) {
            Equipment equ = (Equipment) equip;
            System.out.printf("%s %s %d\n", equ.getType(), equ.getName(), equ.getEffect());
        }
        objects.remove(equId);
        bag.remove(equId);
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

    public void fail(String bottleName) {
        System.out.printf("%s fail to use %s\n",name,bottleName);
    }

    public Bottle opType(String boIdd, String boName, String type, String cap, String cee) {
        int botId = Integer.parseInt(boIdd);
        int capNum = Integer.parseInt(cap);
        int ce = Integer.parseInt(cee);
        Bottle bottle = null;

        switch (type) {
            case "HpBottle":
                bottle = new HpBottle(botId, boName, capNum, type, ce);
                break;
            case "AtkBottle":
                bottle = new AtkBottle(botId, boName, capNum, type, ce);
                break;
            case "DefBottle":
                bottle = new DefBottle(botId, boName, capNum, type, ce);
                break;
            default:
                break;
        }
        return bottle;
    }

    public Equipment opEquip(String idd, String name, String type, String num, String cee) {
        int id = Integer.parseInt(idd);
        int capNum = Integer.parseInt(num);
        int ce = Integer.parseInt(cee);

        return new Equipment(id, name, type, capNum, ce);
    }

    //操作二
    public void operationTwo(Bottle newBottle, Adventurer markedAd) {
        markedAd.add(newBottle);//为一名冒险者的物品栏添加一个药水瓶
    }

    //操作三
    public void operationThree(Equipment newE, Adventurer markedAd) {
        markedAd.add(newE);//为一名冒险者添加一个装备
    }

    //操作四
    public void operationFour(int enhId, Adventurer markedAd) {
        markedAd.enhDur(enhId);//提升冒险者的某个装备的耐久度
    }

    //操作五
    public void operationFive(int delId, Adventurer markedAd) {
        OBject delObject = markedAd.getObject(delId);//用来判断要删除的是否是药水瓶
        if (delObject instanceof Bottle) {
            markedAd.del(delId,1);
        } else {
            markedAd.del(delId);
        }
    }

    //操作六
    public void operationSix(int takeId, Adventurer markedAd) {
        if (!markedAd.isInBag(takeId)) {
            markedAd.take(markedAd.getObject(takeId));//假如没在背包中，那就将其加入背包中；反之，无所谓
        }
    }

    //操作七
    public void operationSeven(int useId, Adventurer markedAd) {
        OBject useBottle = markedAd.getObject(useId);
        if (useBottle instanceof Bottle) {
            Bottle bottle = (Bottle) useBottle;
            if (!markedAd.isInBag(bottle.getId())) {
                markedAd.fail(bottle.getName());
            } else {
                if (bottle.getIsEmpty() == 1) {
                    markedAd.success();
                    markedAd.del(bottle.getId(),0);
                } else {
                    markedAd.enhAttr(bottle);
                    markedAd.success();
                    bottle.setIsEmpty();
                }
            }
        }
    }
}
