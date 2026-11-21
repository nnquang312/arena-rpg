package model.character;

public class Mage extends Hero {

    public Mage(String name) {
        super(name, 80, 20, 5, 100);
    }

    @Override
    public int calculateDamage() {
        return getAttack() + getMana() / 10;
    }

    @Override
    public String toString() {
        return "Mage: " + getName() + ", HP: " + getHp();
    }

}
