public class ShdTreasure implements Treasure {
    @Override
    public void showInfo() {
        System.out.println("Cloak of Shadows");
    }

    @Override
    public void useBy(Adventurer adventurer) {
        adventurer.setDef(40);
        for (Adventurer ad: adventurer.getWorkers()) {
            ad.setDef(40);
        }
    }
}
