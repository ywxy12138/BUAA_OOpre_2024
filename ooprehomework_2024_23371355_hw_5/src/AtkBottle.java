public class AtkBottle extends Bottle {
    private final int attribute;

    public AtkBottle(int id, String name, int capacity, String type, int ce) {
        super(id, name, type, capacity, ce);
        this.attribute = ce + capacity / 100;
    }

    @Override
    public int getCe() {
        return attribute;
    }
}
