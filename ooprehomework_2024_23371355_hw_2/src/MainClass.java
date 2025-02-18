import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class MainClass {
    private static ArrayList<Adventurer> adventurers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<String>> inputInf = new ArrayList<>();//经过按空格分割解析后的输入将会存储进该容器中，就像二位数组一样

        int n = Integer.parseInt(scanner.nextLine().trim());
        /*读取行数,Integer.parseInt()将字符串转换为数字，
        括号内的.trim()是使字符串规范化即去掉字符串首尾的空白的方法
        */
        for (int i = 0; i < n; ++i) {
            String nextLine = scanner.nextLine();//读取一行的指令
            String [] strings = nextLine.trim().split(" +");//按空格对本行进行分割
            inputInf.add(new ArrayList<>((Arrays.asList(strings))));
            /*指令分割后的各个部分按列表的形式分别存进容器inputInf中
             */
        }

        for (int i = 0; i < n; ++i) {
            operateOp(inputInf,i);
        }

    }

    //按Id找到一个冒险者
    public static Adventurer getAdId(int adventurerId) {
        Adventurer holdAdventurer = null;

        for (Adventurer adventurer : adventurers) {
            if (adventurer.getId() == adventurerId) {
                holdAdventurer = adventurer;
                break;
            }
        }

        return holdAdventurer;
    }

    public static void operateOp(ArrayList<ArrayList<String>> inputInf,int i) {
        switch (inputInf.get(i).get(0)) {
            case "1":
                int newAdId = Integer.parseInt(inputInf.get(i).get(1));//冒险者的id
                Adventurer newAd = new Adventurer(newAdId, inputInf.get(i).get(2));
                adventurers.add(newAd);//添加一个冒险者
                break;
            case "2": {
                Adventurer markedAd = getAdId(Integer.parseInt(inputInf.get(i).get(1)));
                int newBotId = Integer.parseInt(inputInf.get(i).get(2));
                int capNum = Integer.parseInt(inputInf.get(i).get(4));//药水瓶的容量
                Bottle newBottle = new Bottle(newBotId, inputInf.get(i).get(3), capNum);
                markedAd.addBot(newBottle);//为一名冒险者添加一个药水瓶
                break;
            }
            case "3": {
                Adventurer markedAd = getAdId(Integer.parseInt(inputInf.get(i).get(1)));
                int newEquipId = Integer.parseInt(inputInf.get(i).get(2));
                int durNum = Integer.parseInt(inputInf.get(i).get(4));//装备的耐久度
                Equipment newEquip = new Equipment(newEquipId, inputInf.get(i).get(3), durNum);
                markedAd.addEquip(newEquip);//为一名冒险者添加一个装备
                break;
            }
            case "4": {
                Adventurer markedAd = getAdId(Integer.parseInt(inputInf.get(i).get(1)));
                int enhId = Integer.parseInt(inputInf.get(i).get(2));
                markedAd.enhDur(enhId);//提升冒险者的某个装备的耐久度
                break;
            }
            case "5": {
                Adventurer markedAd = getAdId(Integer.parseInt(inputInf.get(i).get(1)));
                int delBotId = Integer.parseInt(inputInf.get(i).get(2));
                markedAd.delBot(delBotId);//删除冒险者的一个药水瓶
                break;
            }
            case "6": {
                Adventurer markedAd = getAdId(Integer.parseInt(inputInf.get(i).get(1)));
                int delEquId = Integer.parseInt(inputInf.get(i).get(2));
                markedAd.delEqu(delEquId);//删除冒险者的一个装备
                break;
            }
            default: {
                break;
            }
        }
    }
}
