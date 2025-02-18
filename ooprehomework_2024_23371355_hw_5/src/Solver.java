import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.util.HashSet;

public class Solver {
    private final Scanner scanner;
    private static HashMap<Integer,Adventurer> adventurers = new HashMap<>();

    public Solver(Scanner scan) {
        this.scanner = scan;
    }

    public void solve() {
        ArrayList<ArrayList<String>> inInf = new ArrayList<>();//经过按空格分割解析后的输入将会存储进该容器中，就像二位数组一样

        int n = Integer.parseInt(scanner.nextLine().trim());
        /*读取行数,Integer.parseInt()将字符串转换为数字，
        括号内的.trim()是使字符串规范化即去掉字符串首尾的空白的方法
        */
        for (int i = 0; i < n; ++i) {
            String nextLine = scanner.nextLine();//读取一行的指令
            String [] strings = nextLine.trim().split(" +");//按空格对本行进行分割
            inInf.add(new ArrayList<>((Arrays.asList(strings))));
            /*指令分割后的各个部分按列表的形式分别存进容器inInf中
             */
        }

        for (int i = 0; i < n; ++i) {
            operateOp(inInf.get(i));
        }
    }

    public static void operateOp(ArrayList<String> in) {
        Adventurer markedAd = adventurers.get(Integer.parseInt(in.get(1)));
        switch (in.get(0)) {
            case "1": {
                int newAdId = Integer.parseInt(in.get(1));//冒险者的id
                Adventurer newAd = new Adventurer(newAdId, in.get(2));
                adventurers.put(newAdId,newAd);//添加一个冒险者
                break;
            }
            case "2": {
                operationTwo(opType(in.get(2),in.get(3),in.get(5),in.get(4),in.get(6)),markedAd);
                break;
            }
            case "3": {
                Equipment e = opEquip(in.get(2),in.get(3),in.get(5),in.get(4),in.get(6));
                operationThree(e,markedAd);
                //为该冒险者添加一个装备
                break;
            }
            case "4": {
                operationFour(Integer.parseInt(in.get(2)),markedAd);
                break;
            }
            case "5": {
                operationFive(Integer.parseInt(in.get(2)),markedAd);
                break;
            }
            case "6": {
                operationSix(Integer.parseInt(in.get(2)),markedAd);
                break;
            }
            case "7": {
                operationSeven(Integer.parseInt(in.get(2)),markedAd);
                break;
            }
            case "8": {
                Fragment fragment = new Fragment(Integer.parseInt(in.get(2)), in.get(3));
                operationEight(fragment,markedAd);
                break;
            }
            case "9": {
                opFrag(in.get(2), Integer.parseInt(in.get(3)), markedAd);
                break;
            }
            case "10": {
                operationTen(in,markedAd);
                break;
            }
            case "11": {
                operationEleven(Integer.parseInt(in.get(2)),markedAd);
                break;
            }
            case "12": {
                operationTwelve(markedAd);
                break;
            }
            default: {
                break;
            }
        }
    }

    public static void add(Adventurer adventurer) {
        adventurers.put(adventurer.getId(),adventurer);
    }

    public static HashMap<Integer,Adventurer> getAdventurers() {
        return adventurers;
    }

    public static Bottle opType(String boIdd, String boName, String type, String cap, String cee) {
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

    public static Equipment opEquip(String idd, String name, String type, String num, String cee) {
        int id = Integer.parseInt(idd);
        int capNum = Integer.parseInt(num);
        int ce = Integer.parseInt(cee);
        Equipment equipment = null;

        switch (type) {
            case "Axe":
                equipment = new Axe(id, name, type, capNum, ce);
                break;
            case "Sword":
                equipment = new Sword(id, name, type, capNum, ce);
                break;
            case "Blade":
                equipment = new Blade(id, name, type, capNum, ce);
                break;
            default: break;
        }
        return equipment;
    }

    public static void opFrag(String fragName,int welfareId, Adventurer markedAd) {
        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for (Map.Entry<Integer, Fragment> entry : markedAd.getFragments().entrySet()) {
            if (entry.getValue().getName().equals(fragName)) {
                cnt++;
                list.add(entry.getKey());
            }
        }
        if (cnt < 5) {
            markedAd.fail(cnt);
        }
        else {
            OBject object = markedAd.getObject(welfareId);
            char c;
            if (object != null) {
                if (object instanceof Bottle) {
                    c = 'a';
                }
                else {
                    c = 'b';
                }
            }
            else {
                c = 'c';
            }
            markedAd.success(c, welfareId, fragName, markedAd);
            markedAd.del(list);
        }
    }

    //操作二
    public static void operationTwo(Bottle newBottle, Adventurer markedAd) {
        markedAd.add(newBottle);
    }
    //为一名冒险者的物品栏添加一个药水瓶

    //操作三
    public static void operationThree(Equipment newE, Adventurer markedAd) {
        markedAd.add(newE);//为一名冒险者添加一个装备
    }

    //操作四
    public static void operationFour(int enhId, Adventurer markedAd) {
        markedAd.enhDur(enhId);//提升冒险者的某个装备的耐久度
    }

    //操作五
    public static void operationFive(int delId, Adventurer markedAd) {
        OBject delObject = markedAd.getObject(delId);//用来判断要删除的是否是药水瓶
        if (delObject instanceof Bottle) {
            markedAd.del(delId,1);//参数1代表要有输出
        } else {
            markedAd.del(delId);
        }
    }

    //操作六
    public static void operationSix(int takeId, Adventurer markedAd) {
        OBject takeObject = markedAd.getObject(takeId);

        if (!markedAd.isInBag(takeObject)) {
            markedAd.take(markedAd.getObject(takeId));//假如没在背包中，那就将其加入背包中；反之，无所谓
        }
    }

    //操作七
    public static void operationSeven(int useId, Adventurer markedAd) {
        OBject useBottle = markedAd.getObject(useId);
        if (useBottle instanceof Bottle) {
            Bottle bottle = (Bottle) useBottle;
            if (!markedAd.isInBag(bottle)) {
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

    //操作八
    public static void operationEight(Fragment fragment, Adventurer markedAd) {
        markedAd.add(fragment);
    }

    //fight
    public static void operationTen(ArrayList<String> in, Adventurer markedAd) {
        String name = in.get(2);
        String type = in.get(3);
        Equipment equipment = null;
        for (Map.Entry<Integer,Equipment> entry : markedAd.getEquipbag().entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                equipment = entry.getValue();
                break;
            }
        }
        int k = Integer.parseInt(in.get(4));
        ArrayList<Adventurer> list = new ArrayList<>();
        int maxDef = 0;
        for (int i = 0; i < k; ++i) {
            Adventurer ad = Solver.getAdventurers().get(Integer.parseInt(in.get(i + 5)));
            list.add(ad);
            if (ad.getDef() > maxDef) {
                maxDef = ad.getDef();
            }
        }
        if (type.equalsIgnoreCase("chain")) {
            maxDef = chaFight(list);
        }
        if (equipment != null && markedAd.getAtk() + equipment.getCE() > maxDef) {
            markedAd.success(type,equipment,list,markedAd);
        }
        else {
            markedAd.fightFail(markedAd.getId());
        }
    }

    public static int chaFight(ArrayList<Adventurer> list) {
        HashSet<Adventurer> defenders = new HashSet<>();
        for (Adventurer adventurer : list) {
            defenders.add(adventurer);
        }
        //去重
        list.clear();
        for (Adventurer adventurer : defenders) {
            list.add(adventurer);
        }
        //清空列表，装去重后的冒险者
        defenders.clear();
        for (Adventurer ad : list) {
            defenders.add(ad);
            dfs(1,defenders,ad);
        }
        int max = 0;
        for (Adventurer ad : defenders) {
            if (ad.getDef() > max) {
                max = ad.getDef();
            }
            if (!list.contains(ad)) {
                list.add(ad);
            }
        }
        return max;
    }

    public static void dfs(int num, HashSet<Adventurer> set, Adventurer worker) {
        set.add(worker);
        if (num < 5) {
            if (!worker.getWorkers().isEmpty()) {
                for (Adventurer ad : worker.getWorkers()) {
                    dfs(num + 1, set, ad);
                }
            }
        }
    }

    //操作十一
    public static void operationEleven(int workerId, Adventurer adventurer) {
        Adventurer worker = adventurers.get(workerId);
        adventurer.addWorker(worker);
    }

    //操作十二
    public static void operationTwelve(Adventurer adventurer) {
        Guard shd = new Shd();
        Guard stn = new Stn();
        Guard flm = new Flm();
        Guard frz = new Frz();
        Guard wnd = new Wnd();
        if (shd.fight(adventurer)) {
            Treasure treasure1 = TreasureFactory.createTreasure(shd);
            treasure1.showInfo();
            treasure1.useBy(adventurer);
            if (flm.fight(adventurer)) {
                Treasure treasure2 = TreasureFactory.createTreasure(flm);
                treasure2.showInfo();
                treasure2.useBy(adventurer);
                if (stn.fight(adventurer)) {
                    Treasure treasure3 = TreasureFactory.createTreasure(stn);
                    treasure3.showInfo();
                    treasure3.useBy(adventurer);
                    if (wnd.fight(adventurer)) {
                        Treasure treasure4 = TreasureFactory.createTreasure(wnd);
                        treasure4.showInfo();
                        treasure4.useBy(adventurer);
                        if (frz.fight(adventurer)) {
                            Treasure treasure5 = TreasureFactory.createTreasure(frz);
                            treasure5.showInfo();
                            treasure5.useBy(adventurer);
                        }
                    }
                }
            }
        }
    }
}
