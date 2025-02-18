public class Sword extends Equipment {
    private int ce;

    public Sword(int id, String name, String type, int durability,int ce) {
        super(id, name, type, durability, ce);
        this.ce = ce;
    }

    public int getHipDamage(int atk, int def) {
        return ce + atk - def;
    }
}
