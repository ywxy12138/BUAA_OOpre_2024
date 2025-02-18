class Player {
    private final Pet pet;
    private int exp;

    public Player(Pet pet) {
        this.pet = pet;
        exp = 0;
    }

    public void feedPet(String foodName) {
        final int FEED_EXP = 5;

        pet.eat(foodName);
        exp += FEED_EXP;
    }

    public void playWithPet() {
        final int PLAY_EXP = 7;

        pet.play();
        exp += PLAY_EXP;
    }

    public void checkPetStatus() {
        pet.printStatus();
    }

    public void printStatus() {
        System.out.println("Final Player Exp: " + exp);
    }
}
