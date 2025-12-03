package components;

public class Axebringer extends Warrior {
    public static double BASE_HEALTH;
    public static int BASE_COST;
    public static int WEAPON_TYPE = 2;
    public static int BASE_ATTACK_DAMAGE;

    public Axebringer (Tile position) {
        super(position, BASE_HEALTH, WEAPON_TYPE, BASE_ATTACK_DAMAGE, BASE_COST);
    }

    public int takeAction() {
        double skillPoints = 0;
        Monster firstMonsterOnTile = this.getPosition().getMonster();
        boolean skipTurn = false;
           //   Condition checks if axebringer threw is axe (one turn skipped).
        if (skipTurn == false) {
            if (firstMonsterOnTile != null) {
                double damageInduced = firstMonsterOnTile.takeDamage((double) BASE_ATTACK_DAMAGE, WEAPON_TYPE);
                skillPoints = 1 + (BASE_ATTACK_DAMAGE / damageInduced);
            } else {
                Monster firstMonsterNextTile = this.getPosition().towardTheCamp().getMonster();
                if (firstMonsterNextTile != null && this.getPosition().towardTheCamp().isCamp() == false) {
                    double damageInduced = firstMonsterNextTile.takeDamage((double) BASE_ATTACK_DAMAGE, WEAPON_TYPE);
                    skillPoints = 1 + (BASE_ATTACK_DAMAGE / damageInduced);
                    skipTurn = true;
                }
            }
        }
        skipTurn = false;
        return (int) skillPoints;
    }
}
