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

public class Main {
    public static void main(String[] args) {
        Team team = new Team();
        team.addMember(new Warrior("Arthur"));
        team.addMember(new Mage("Merlin"));
        team.addMember(new Archer("Robin"));

        List<Monster> monsters = List.of(new Goblin(), new Orc(), new Dragon());

        new Battle(team, monsters, new ConsoleUI()).start();
    }
}