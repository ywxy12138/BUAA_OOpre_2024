public class WndTreasure implements Treasure {
    @Override
    public void showInfo() {
        System.out.println("Windrunner Boots");
    }

    @Override
    public void useBy(Adventurer adventurer) {
        adventurer.setDef(30);
        for (Adventurer ad: adventurer.getWorkers()) {
            ad.setDef(30);
        }
    }
}
