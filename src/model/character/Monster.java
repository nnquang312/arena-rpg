package model.character;

public abstract class Monster extends Characters {

    private final int expDrop;

    public Monster(String name, int maxHP, int attack, int defense, int expDrop) {
        super(name, maxHP, attack, defense);
        this.expDrop = expDrop;
    }

    public int getExpDrop() {
        return expDrop;
    }
}