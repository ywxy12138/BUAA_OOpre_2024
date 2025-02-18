public class Flm implements Guard {
    @Override
    public boolean fight(Adventurer adventurer) {
        return adventurer.getComCe(adventurer) > 2000;
    }

    @Override
    public String getName() {
        return "Flm";
    }
}