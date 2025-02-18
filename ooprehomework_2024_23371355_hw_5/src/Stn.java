public class Stn implements Guard {
    @Override
    public boolean fight(Adventurer adventurer) {
        return adventurer.getComCe(adventurer) > 3000;
    }

    @Override
    public String getName() {
        return "Stn";
    }
}