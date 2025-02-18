public class Equipment {
    private final int id;
    private final String name;
    private int durability;

    public Equipment(int id, String name, int durability) {
        this.id = id;
        this.name = name;
        this.durability = durability;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDur() {
        return durability;
    }

    public void enhDur() {
        durability += 1;
        System.out.println(name + " " + durability);
    }
}
