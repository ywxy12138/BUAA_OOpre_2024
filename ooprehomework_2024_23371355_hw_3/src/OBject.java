public class OBject { //物品类，方便构建背包系统，实现在背包内的检索
    private final int id;
    private final String name;
    private final String type;
    private int effect;
    private final int ce;

    public OBject(int id, String name, String type, int effect, int ce) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.effect = effect;
        this.ce = ce;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getEffect() { //更加一般的调用药水瓶或装备的性能
        return effect;
    }

    public int getCe() {
        return ce;
    }
}
