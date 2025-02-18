public class HpBottle extends Bottle {
    private final int attribute;

    public HpBottle(int id, String name, int capacity, String type, int ce) {
        super(id, name, type, capacity, ce);
        this.attribute = capacity;
    }

    @Override
    public int getCe() {
        return attribute;
    }
}