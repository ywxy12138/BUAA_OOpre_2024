public class Bottle extends OBject implements CE {
    private int isEmpty;

    public Bottle(int id, String name, String type, int capacity,int ce) {
        super(id,name,type,capacity,ce);
        this.isEmpty = 0;
    }

    public int getIsEmpty() {
        return isEmpty;
    }

    @Override
    public int getCE() {
        return super.getCe();
    }

    public void setIsEmpty() {
        isEmpty = 1;
    }

}
