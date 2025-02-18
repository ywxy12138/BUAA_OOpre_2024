public class Wnd implements Guard {
    @Override
    public boolean fight(Adventurer adventurer) {
        return adventurer.getComCe(adventurer) > 4000;
    }

    @Override
    public String getName() {
        return "Wnd";
    }
}