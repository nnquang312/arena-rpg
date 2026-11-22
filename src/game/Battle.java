package game;

import java.util.List;
import java.util.Random;

import model.character.Hero;
import model.character.Monster;
import ui.ConsoleUI;

public class Battle {

    private final Team team;
    private final List<Monster> monsters;
    private final ConsoleUI ui;
    private final Random rng = new Random();

    public Battle(Team team, List<Monster> monsters, ConsoleUI ui) {
        this.team = team;
        this.monsters = monsters;
        this.ui = ui;
    }

    public void start() {
        int round = 1;
        for (Monster enemy : monsters) {
            ui.showMessage("\n=== Vòng " + round + ": " + enemy.getName() + " xuất hiện! ===");
            fight(enemy);

            if (!team.isAlive()) {
                ui.showMessage("\nCả đội đã gục ngã...");
                return;
            }
            team.gainExp(enemy.getExpDrop());
            ui.showMessage("Đội nhận " + enemy.getExpDrop() + " exp!");
            round++;
        }
        ui.showMessage("\nĐội của bạn đã chiến thắng đấu trường!");
    }

    private void fight(Monster enemy) {
        while (team.isAlive() && enemy.isAlive()) {
            for (Hero hero : team.getAliveMembers()) {
                heroTurn(hero, enemy);
                if (!enemy.isAlive()) {
                    ui.showMessage(enemy.getName() + " đã bị hạ!");
                    return;
                }
            }
            monsterTurn(enemy);
        }
    }

    private void heroTurn(Hero hero, Monster enemy) {
        ui.showStatus(hero, enemy);
        int action = ui.askAction();
        if (action == 1) {
            int before = enemy.getHp();
            hero.attack(enemy);
            int dealt = before - enemy.getHp();
            ui.showMessage(hero.getName() + " đánh " + enemy.getName() + ", gây " + dealt + " sát thương");
        } else {
            int skill = ui.askSkill();
            ui.showMessage(hero.useSkills(enemy, skill)); // in chuỗi mà skill trả về
        }
    }

    private void monsterTurn(Monster enemy) {
        List<Hero> alive = team.getAliveMembers();
        Hero target = alive.get(rng.nextInt(alive.size()));
        int before = target.getHp();
        enemy.attack(target);
        int dealt = before - target.getHp();
        ui.showMessage(enemy.getName() + " tấn công " + target.getName()
                + ", gây " + dealt + " sát thương (còn " + target.getHp() + " HP)");
    }
}