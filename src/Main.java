import model.character.Mage;
import model.character.Warrior;

public class Main {
    public static void main(String[] args) {
        // Create a Mage character
        Mage mage = new Mage("Gandalf");

        // Create a Warrior character
        Warrior warrior = new Warrior("Conan");

        while (mage.isAlive() && warrior.isAlive()) {
            // Mage attacks Warrior
            mage.attack(warrior, 0);
            System.out.println(
                    mage.getName() + " attacks " + warrior.getName() + " for " + mage.calculateDamage() + " damage.");
            System.out.println(warrior.getName() + " has " + warrior.getHp() + " HP left.");

            if (!warrior.isAlive()) {
                System.out.println(warrior.getName() + " has been defeated!");
                break;
            }

            // Warrior attacks Mage
            warrior.attack(mage, 0);
            System.out.println(warrior.getName() + " attacks " + mage.getName() + " for " + warrior.calculateDamage()
                    + " damage.");
            System.out.println(mage.getName() + " has " + mage.getHp() + " HP left.");

            if (!mage.isAlive()) {
                System.out.println(mage.getName() + " has been defeated!");
                break;
            }
        }
    }
}
