public class Blade extends Equipment {
    private int ce;

    public Blade(int id, String name, String type, int durability,int ce) {
        super(id, name, type, durability, ce);
        this.ce = ce;
    }

    public int getHipDamage(int atk) {
        return ce + atk;
    }
}
