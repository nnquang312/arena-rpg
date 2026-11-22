package model.character;

public abstract class Hero extends Characters {
    private int level;
    private int exp;
    private int mana;
    private int maxMana;
    // private final List<String> skills = new ArrayList<>();

    // Constructor for Hero class
    public Hero(String name, int maxHP, int attack, int defense, int maxMana) {
        super(name, maxHP, attack, defense);

        this.maxMana = maxMana;
        this.mana = maxMana;
        this.level = 1;
        this.exp = 0;
    }

    // overide method useSkills
    // public abstract String useSkills(Characters target);

    // Method
    public void gainExp(int amount) {
        this.exp += amount;
        while (this.exp >= 100) {
            this.exp -= 100;
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        increaseMaxHP(10);
        heal(10);
        increaseAttack(5);
        this.maxMana += 5;
        restoreMana(5);
    }

    public boolean useMana(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Mana cost cannot be negative");
        }
        if (amount > this.mana) {
            return false;
        }
        this.mana -= amount;
        return true;
    }

    public void restoreMana(int amount) {
        this.mana = Math.min(this.maxMana, this.mana + amount);
    }

    // Getters for Hero class

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

}
