package model.skill;
import model.character.Characters;


public abstract class skills {
    private final String name;
    private final int manaCost;

    public skills (String name, int manaCost){
        this.name = name;
        this.manaCost = manaCost;   
    }


// Getters for skills class
    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    protected abstract String execute(Hero hero, Monster monster);

    public final String useSkill(Hero hero, Monster monster) {
        if (hero.useMana(manaCost)) {
            return execute(hero, monster);
        } else {
            return hero.getName() + " does not have enough mana to use " + name + ".";
        }
    }


}
