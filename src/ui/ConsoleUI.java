package ui;

import java.util.Scanner;

import model.character.Hero;
import model.character.Monster;

public class ConsoleUI {

    private final Scanner sc = new Scanner(System.in);

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showStatus(Hero hero, Monster enemy) {
        System.out.println("\n--- Lượt của " + hero.getName() + " ---");
        System.out.println(hero.getName() + " | HP " + hero.getHp() + "/" + hero.getMaxHP()
                + " | Mana " + hero.getMana() + "/" + hero.getMaxMana());
        System.out.println(enemy.getName() + " | HP " + enemy.getHp() + "/" + enemy.getMaxHP());
    }

    public int askAction() {
        System.out.println("1. Đánh thường   2. Dùng kỹ năng");
        return readInt(1, 2);
    }

    public int askSkill() {
        System.out.println("Chọn kỹ năng (1 đến 3):");
        return readInt(1, 3);
    }

    private int readInt(int min, int max) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                int n = Integer.parseInt(line);
                if (n >= min && n <= max)
                    return n;
            } catch (NumberFormatException e) {
                // rơi xuống thông báo bên dưới
            }
            System.out.println("Hãy nhập số từ " + min + " đến " + max + ":");
        }
    }
}