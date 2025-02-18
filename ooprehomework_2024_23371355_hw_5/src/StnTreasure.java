public class StnTreasure implements Treasure {
    @Override
    public void showInfo() {
        System.out.println("Stoneheart Amulet");
    }

    @Override
    public void useBy(Adventurer adventurer) {
        adventurer.setDef(40);
        for (Adventurer ad: adventurer.getWorkers()) {
            ad.setDef(40);
        }
    }
}
