package model.character;

public abstract class Hero extends Characters {
    private int level;
    private int exp;
    private int mana;
    private int maxMana;
    // private final List<String> skills = new ArrayList<>();

    public Hero(String name, int maxHP, int attack, int defense, int maxMana) {
        super(name, maxHP, attack, defense);

        this.maxMana = maxMana;
        this.mana = maxMana;
        this.level = 1;
        this.exp = 0;
    }

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

    public void useMana(int amount) {
        if (amount <= this.mana) {
            this.mana -= amount;
        } else {
            throw new IllegalArgumentException("Not enough mana");
        }
    }

    public void restoreMana(int amount) {
        this.mana = Math.min(this.maxMana, this.mana + amount);
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getMana() {
        return mana;
    }

}
