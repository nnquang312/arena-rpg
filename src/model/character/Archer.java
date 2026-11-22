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
    public void useSkills(Characters target, int choice) {
        switch (choice) {
            case 1: {
                if(!useMana(15)){
                    System.out.println(getName() + " không đủ mana để dùng skill 1!");
                    return;
                }
                int total = 0;
                for (int i = 0; i < 2; i++) {
                    if(!target.isAlive()) break;
                    int dmg = Math.max(0, getAttack() - target.getDefense());
                    target.takeDamage(dmg);
                    total += dmg;
                }
                System.out.println(getName() + " dùng skill 1, gây tổng cộng " + total + " sát thương!");
                break;
            }
            case 2: {
                if (!useMana(25)) {
                    System.out.println(getName() + " không đủ mana để dùng skill 2!");
                    return;
                }
                int dmg = getAttack();
                target.takeDamage(dmg);
                System.out.println(getName() + " dùng skill 2, gây " + dmg + " sát thương!");
                break;
            }
            case 3: {
                if (!useMana(40)) {
                    System.out.println(getName() + " không đủ mana để dùng skill 3!");
                    return;
                }
                int total = 0;
                for (int i = 0; i < 3; i++) {
                    if(!target.isAlive()) break;
                    int dmg = Math.max(0, getAttack() - target.getDefense());
                    target.takeDamage(dmg);
                    total += dmg;
                }
                System.out.println(getName() + " dùng skill 3, gây tổng cộng " + total + " sát thương!");
                break;
            }
            default:
                System.out.println("Skill không tồn tại!");
        }
    }


    @Override
    public String toString() {
        return "Archer: " + getName() + ", HP: " + getHp();
    }

}
