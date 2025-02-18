public class FlmTreasure implements Treasure {
    @Override
    public void showInfo() {
        System.out.println("Flamebrand Sword");
    }

    @Override
    public void useBy(Adventurer adventurer) {
        adventurer.setAtk(40);;
        for (Adventurer ad: adventurer.getWorkers()) {
            ad.setAtk(40);
        }
    }
}
