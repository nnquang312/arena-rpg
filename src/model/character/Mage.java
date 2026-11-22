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

    private void countSkillsUsed() {
        countSkills++;
        if (countSkills >= 5) {
            isCriticalHit = true;
            countSkills = 0;
        }
    }

    // useSkills method for Mage, 3 skills with different mana costs and effects
    @Override
    public void useSkills(Characters target, int choice) {
        switch (choice) {
            case 1: {
                if (useMana(30)) {
                    int dmg = getAttack() * 2;
                    target.takeDamage(dmg);
                    countSkillsUsed();
                }
                System.out.println(getName() + " không đủ mana để dùng skill 1!");
                break;
            }
            case 2: {
                if (useMana(25)) {
                    this.heal(30);
                    countSkillsUsed();
                }
                System.out.println(getName() + " không đủ mana để dùng skill 2!");
                break;
            }
            case 3: {
                if (useMana(50)) {
                    int dmg = getAttack() * 3 - target.getDefense();
                    target.takeDamage(dmg);
                    countSkillsUsed();
                }
                System.out.println(getName() + " không đủ mana để dùng skill 3!");
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Mage: " + getName() + ", HP: " + getHp();
    }

}
