package model.character;

public class Archer extends Characters {

    private int agility;

    public Archer(String name) {
        super(name, 100, 12, 8);
        this.agility = 15; // Set a default agility value
    }

    @Override
    public int calculateDamage() {
        return getAttack() + agility / 2;
    }

    @Override
    public String toString() {
        return "Archer: " + getName() + ", HP: " + getHp();
    }

}
