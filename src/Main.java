import model.character.Mage;
import model.character.Warrior;

public class Main {
    public static void main(String[] args) {
        // Create characters
        Warrior warrior = new Warrior("Conan");
        Mage mage = new Mage("Gandalf");

        while (warrior.isAlive() && mage.isAlive()) {
            // Warrior attacks Mage
            warrior.attack(mage);
            System.out.println(warrior + " attacks " + mage + ". " + mage.getName() + " HP: " + mage.getHp());

            if (!mage.isAlive()) {
                System.out.println(mage.getName() + " has been defeated!");
                break;
            }

            // Mage attacks Warrior
            mage.attack(warrior);
            System.out.println(mage + " attacks " + warrior + ". " + warrior.getName() + " HP: " + warrior.getHp());

            if (!warrior.isAlive()) {
                System.out.println(warrior.getName() + " has been defeated!");
                break;
            }
        }
    }
}
