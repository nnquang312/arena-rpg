package model.character;


public class Dragon extends Monster {
    private int turnCount;

    public Dragon() {
        super("Dragon", 400, 40, 12, 150);
        this.turnCount = 0;
    }

    @Override
    public int calculateDamage() {
        int damage = getAttack();
        if (turnCount == 2) { // lượt thứ 3: phun lửa
            damage *= 2;
        }
        return damage;
    }

    @Override
    public void attack(Characters target, int bonus) {
        super.attack(target, bonus);
        turnCount++;
        if (turnCount == 3) {
            turnCount = 0;
        }
    }
}
