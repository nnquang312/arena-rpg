package model.character;

public class Warrior extends Hero {

    private int armor;

    public Warrior(String name) {
        super(name, 120, 15, 10, 50);
        this.armor = 20; // Set a default armor valu
    }

    @Override
    public int calculateDamage() {
        return getAttack() + armor / 2;
    }

    @Override
    public String toString() {
        return "Warrior: " + getName() + ", HP: " + getHp();
    }

}
