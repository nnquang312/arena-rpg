package model.character;

public abstract class Characters {

    private final String name;
    private int hp;
    private int maxHP;
    private int attack;
    private final int defense;

    public Characters(String name, int maxHP, int attack, int defense) {
        this.name = name;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.attack = attack;
        this.defense = defense;
    }

    public void takeDamage(int damage) {
        this.hp = Math.max(0, this.hp - damage);
    }

    public void heal(int amount) {
        this.hp = Math.min(this.maxHP, this.hp + amount);
    }

    public void increaseAttack(int amount) {
        this.attack += amount;
    }

    protected void increaseMaxHP(int amount) {
        this.maxHP += amount;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    // Overloading: cùng tên attack, khác danh sách tham số.
    // [Sửa] Bản không bonus chỉ gọi lại bản có bonus = 0 -> không lặp code.
    public void attack(Characters target) {
        attack(target, 0);
    }

    public void attack(Characters target, int bonus) {
        target.takeDamage(computeDamage(target, bonus));
    }

    private int computeDamage(Characters target, int bonus) {
        return Math.max(0, calculateDamage() + bonus - target.defense);
    }

    public int calculateDamage() {
        return this.attack;
    }

    public int calculateDamage(Characters target) {
        return computeDamage(target, 0);
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}
