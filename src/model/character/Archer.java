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

    // useSkills method for Archer, 3 skills with different mana costs and effects
    @Override
    public String useSkills(Characters target, int choice) {
        switch (choice) {
            case 1: {
                if (!useMana(15)) {
                    return getName() + " không đủ mana để dùng skill 1!";
                }
                int total = 0;
                for (int i = 0; i < 2; i++) {
                    if (!target.isAlive())
                        break;
                    int dmg = Math.max(0,
                            getAttack() - target.getDefense());
                    target.takeDamage(dmg);
                    total += dmg;
                }
                return getName() + " dùng skill 1, gây tổng cộng " + total + " sát thương!";
            }
            case 2: {
                if (!useMana(25)) {
                    return getName() + " không đủ mana để dùng skill 2!";
                }
                int dmg = getAttack();
                target.takeDamage(dmg);
                return getName() + " dùng skill 2, gây " + dmg + " sát thương!";
            }
            case 3: {
                if (!useMana(40)) {
                    return getName() + " không đủ mana để dùng skill 3!";
                }
                int total = 0;
                for (int i = 0; i < 3; i++) {
                    if (!target.isAlive())
                        break;
                    int dmg = Math.max(0, getAttack() - target.getDefense());
                    target.takeDamage(dmg);
                    total += dmg;
                }
                return getName() + " dùng skill 3, gây tổng cộng " + total + " sát thương!";
            }
            default:
                return getName() + " skill không tồn tại!";
        }
    }


    @Override
    public String toString() {
        return "Archer: " + getName() + ", HP: " + getHp();
    }
}