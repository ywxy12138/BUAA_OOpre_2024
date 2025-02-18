import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

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
                Bottle bo = markedAd.opType(in.get(2),in.get(3),in.get(5),in.get(4),in.get(6));
                markedAd.operationTwo(bo,markedAd);
                break;
            }
            case "3": {
                Equipment e = markedAd.opEquip(in.get(2),in.get(3),"Equipment",in.get(4),in.get(5));
                markedAd.operationThree(e,markedAd); //为该冒险者添加一个装备
                break;
            }
            case "4": {
                markedAd.operationFour(Integer.parseInt(in.get(2)),markedAd);
                break;
            }
            case "5": {
                markedAd.operationFive(Integer.parseInt(in.get(2)),markedAd);
                break;
            }
            case "6": {
                markedAd.operationSix(Integer.parseInt(in.get(2)),markedAd);
                break;
            }
            case "7": {
                markedAd.operationSeven(Integer.parseInt(in.get(2)),markedAd);
                break;
            }
            default: {
                break;
            }
        }
    }
}
