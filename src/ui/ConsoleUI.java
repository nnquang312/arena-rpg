package ui;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import model.character.Hero;
import model.character.Monster;

public class ConsoleUI implements GameUI {

    private final Scanner sc = new Scanner(System.in);
    private final PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    @Override
    public void showMessage(String message) {
        out.println(message);
    }

    @Override
    public void showStatus(Hero hero, Monster enemy) {
        out.println("\n--- Lượt của " + hero.getName() + " ---");
        out.println(hero.getName() + " | HP " + hero.getHp() + "/" + hero.getMaxHP()
                + " | Mana " + hero.getMana() + "/" + hero.getMaxMana());
        out.println(enemy.getName() + " | HP " + enemy.getHp() + "/" + enemy.getMaxHP());
    }

    @Override
    public int askAction() {
        out.println("1. Đánh thường   2. Dùng kỹ năng");
        return readInt(1, 2);
    }

    @Override
    public int askSkill() {
        out.println("Chọn kỹ năng (1 đến 3):");
        return readInt(1, 3);
    }

    // Đọc số nguyên trong khoảng [min, max], gõ sai thì hỏi lại, không sập
    private int readInt(int min, int max) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                int n = Integer.parseInt(line);
                if (n >= min && n <= max) {
                    return n;
                }
            } catch (NumberFormatException e) {
                // rơi xuống thông báo bên dưới
            }
            out.println("Hãy nhập số từ " + min + " đến " + max + ":");
        }
    }
}