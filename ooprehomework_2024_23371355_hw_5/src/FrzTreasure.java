public class FrzTreasure implements Treasure {
    @Override
    public void showInfo() {
        System.out.println("Frostbite Staff");
    }

    @Override
    public void useBy(Adventurer adventurer) {
        adventurer.setAtk(50);
        for (Adventurer ad: adventurer.getWorkers()) {
            ad.setAtk(50);
        }
    }
}
