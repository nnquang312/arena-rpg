package model.character;

import java.util.Random;

public class Goblin extends Monster {

    private static final Random RNG = new Random();

    public Goblin() {
        super("Goblin", 80, 18, 3, 30);
    }

    @Override
    public void takeDamage(int damage) {
        if (RNG.nextInt(100) < 20) {
            return; // né, không mất máu
        }
        super.takeDamage(damage);
    }
}