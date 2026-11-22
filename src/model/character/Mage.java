package model.character;

public class Mage extends Hero {

    public Mage(String name) {
        super(name, 800, 10, 20, 400);
    }

    // after useSkills 5 times, the next attack will be a critical hit, atack damage
    // will be x10

    @Override
    public String toString() {
        return "Mage: " + getName() + ", HP: " + getHp();
    }

}
