public class Frz implements Guard {
    @Override
    public boolean fight(Adventurer adventurer) {
        return adventurer.getComCe(adventurer) > 5000;
    }

    @Override
    public String getName() {
        return "Frz";
    }
}