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
    // Chọn skill: 1 = Chém Xoáy, 2 = Sóng Xung Kích, 3 = Tiếng Hô Chiến Trận
    @Override
    public void useSkills(Characters target, int choice) {
        switch (choice) {
            case 1: {
                if (!useMana(20)) {
                    System.out.println(getName() + " không đủ mana để dùng Chém Xoáy!");
                    return;
                }
                int dmg = Math.max(0, (int) (getAttack() * 1.8) - target.getDefense());
                target.takeDamage(dmg);
                System.out.println(getName() + " dùng Chém Xoáy, gây " + dmg + " sát thương!");
                break;
            }
            case 2: {
                if (!useMana(30)) {
                    System.out.println(getName() + " không đủ mana để dùng Sóng Xung Kích!");
                    return;
                }
                int dmg = Math.max(0, getAttack() * 2 - target.getDefense());
                target.takeDamage(dmg);
                System.out.println(getName() + " tung Sóng Xung Kích, gây " + dmg + " sát thương!");
                break;
            }
            case 3: {
                if (!useMana(15)) {
                    System.out.println(getName() + " không đủ mana để dùng Tiếng Hô Chiến Trận!");
                    return;
                }
                increaseAttack(3);
                heal(15);
                System.out.println(getName() + " hô vang, tăng 3 tấn công và hồi 15 máu!");
                break;
            }
            default:
                System.out.println("Skill không tồn tại!");
        }
    }

}
