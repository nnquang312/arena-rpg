package model.character;

public class Mage extends Hero {

    private int countSkills = 0;
    private boolean isCriticalHit = false;

    public Mage(String name) {
        super(name, 120, 10, 4, 150);
    }

    // after useSkills 5 times, the next attack will be a critical hit, atack damage
    // will be x10

    @Override
    public int calculateDamage() {
        if (isCriticalHit) {
            return getAttack() * 10;
        }
        return getAttack();
    }

    @Override
    public void attack(Characters target, int bonus) {
        super.attack(target, bonus);
        isCriticalHit = false;
    }

    private String countSkillsUsed() {
        countSkills++;
        if (countSkills >= 5) {
            isCriticalHit = true;
            countSkills = 0;
            return getName() + " đã sử dụng kỹ năng 5 lần, đòn đánh tiếp theo sẽ là đòn chí mạng!";
        }
        return "";
    }

    // useSkills method for Mage, 3 skills with different mana costs and effects
    @Override
    public String useSkills(Characters target, int choice) {
        switch (choice) {
            case 1: {
                if (!useMana(30)) {
                    return getName() + " không đủ mana để dùng skill 1!";
                }
                int dmg = getAttack() * 2;
                target.takeDamage(dmg);
                return getName() + " dùng skill 1, gây " + dmg + " sát thương!";
            }
            case 2: {
                if (!useMana(25)) {
                    return getName() + " không đủ mana để dùng skill 2!";
                }
                this.heal(30);
                return getName() + " dùng skill 2, hồi 30 HP!" + countSkillsUsed();

            }
            case 3: {
                if (!useMana(50)) {
                    return getName() + " không đủ mana để dùng skill 3!";
                }

                int dmg = getAttack() * 3 - target.getDefense();
                target.takeDamage(dmg);
                return getName() + " dùng skill 3, gây " + dmg + " sát thương!" + countSkillsUsed();
            }
            default:
                return getName() + " skill không tồn tại!";
        }
    }

    @Override
    public String toString() {
        return "Mage: " + getName() + ", HP: " + getHp();
    }

}
