public class Equipment extends OBject implements CE {
    private int durability;

    public Equipment(int id, String name, String type, int durability,int ce) {
        super(id,name,type,durability,ce);
        this.durability = durability;
    }

    @Override
    public int getCE() {
        return super.getCe();
    }

    public void enhDur() {
        this.durability += 1;
        System.out.printf("%s %d\n",super.getName(),durability);
    }

    @Override
    public int getEffect() { //更加一般的调用药水瓶或装备的性能
        return durability;
    }

}
