public class Shd implements Guard {
    @Override
    public boolean fight(Adventurer adventurer) {
        return adventurer.getComCe(adventurer) > 1000;
    }

    @Override
    public String getName() {
        return "Shd";
    }
}