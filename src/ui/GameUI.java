package ui;

import model.character.Hero;
import model.character.Monster;

public interface GameUI {
    void showMessage(String message);
    void showStatus(Hero hero, Monster enemy);
    int askAction();
    int askSkill();
}