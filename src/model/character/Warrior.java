package model.character;

public class Warrior extends Hero {

    private int armor;

    public Warrior(String name) {
        super(name, 220, 28, 12, 60);
        this.armor = 4; // Set a default armor valu
    }

    @Override
    public void takeDamage(int damage) {
        int reduced = Math.max(1, damage - armor); // tối thiểu còn 1
        super.takeDamage(reduced); // nhờ lớp cha trừ máu
    }

    @Override
    public String toString() {
        return "Warrior: " + getName() + ", HP: " + getHp();
    }

    // useSkills method for Warrior, 3 skills with different mana costs and effects

}
