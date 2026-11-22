import java.util.List;

import game.Battle;
import game.Team;
import model.character.Archer;
import model.character.Dragon;
import model.character.Goblin;
import model.character.Mage;
import model.character.Monster;
import model.character.Orc;
import model.character.Warrior;
import ui.ConsoleUI;
import ui.GameUI;
import ui.SwingUI;

public class Main {

    // true = cửa sổ đồ họa, false = chạy trong terminal
    private static final boolean USE_SWING = true;

    public static void main(String[] args) {
        // 1. Tạo đội hero
        Team team = new Team();
        team.addMember(new Warrior("Arthur"));
        team.addMember(new Mage("Merlin"));
        team.addMember(new Archer("Robin"));

        // 2. Tạo danh sách quái cho 3 vòng
        List<Monster> monsters = List.of(new Goblin(), new Orc(), new Dragon());

        // 3. Chọn giao diện
        GameUI ui = USE_SWING ? new SwingUI() : new ConsoleUI();

        // 4. Bắt đầu trận đấu
        Battle battle = new Battle(team, monsters, ui);
        if (USE_SWING) {
            new Thread(battle::start).start(); // luồng riêng để cửa sổ không bị đứng
        } else {
            battle.start(); // terminal chạy thẳng
        }
    }
}