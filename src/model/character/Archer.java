package model.character;

public class Archer extends Hero {

    private int boost;
    private int attackCount;

    public Archer(String name) {
        super(name, 150, 34, 7, 80);
        this.boost = 20; // Set a default boost value
        this.attackCount = 0; // Initialize attack count
    }

    @Override
    public int calculateDamage() {
        int damage = getAttack();
        if (attackCount == 2) { // đây là đòn thứ 3
            damage += boost; // tăng thêm 15 sát thương
        }
        return damage;
    }

    @Override
    public void attack(Characters target, int bonus) {
        super.attack(target, bonus); // đánh bình thường trước
        attackCount++;
        if (attackCount == 3) {
            attackCount = 0; // sau đòn thứ 3 thì đếm lại
        }
    }

    @Override
    public String toString() {
        return "Archer: " + getName() + ", HP: " + getHp();
    }

}
