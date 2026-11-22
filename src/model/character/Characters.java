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
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        this.hp = Math.max(0, this.hp - damage);
    }

    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Healing amount cannot be negative");
        }
        this.hp = Math.min(this.maxHP, this.hp + amount);
    }

    public void increaseAttack(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Attack increase cannot be negative");
        }
        this.attack += amount;
    }

    protected void increaseMaxHP(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Max HP increase cannot be negative");
        }
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
        if (bonus < 0) {
            throw new IllegalArgumentException("Bonus cannot be negative");
        }
        target.takeDamage(computeDamage(target, bonus));
    }

    private int computeDamage(Characters target, int bonus) {
        if (bonus < 0) {
            throw new IllegalArgumentException("Bonus cannot be negative");
        }
        return Math.max(0, calculateDamage() + bonus - target.defense);
    }

    public int calculateDamage() {
        if (this.attack < 0) {
            throw new IllegalArgumentException("Attack cannot be negative");
        }
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
