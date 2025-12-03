package assignment1;

public class Lanceforged extends Warrior {
    public static double BASE_HEALTH;
    public static int BASE_COST, BASE_ATTACK_DAMAGE;
    public static int WEAPON_TYPE = 3;
    private int piercingPower;
    private int actionRange;

    public Lanceforged(Tile position, int piercingPower, int actionRange) {
        super(position, BASE_HEALTH, BASE_COST, WEAPON_TYPE, BASE_ATTACK_DAMAGE);
        this.piercingPower = piercingPower;
        this.actionRange = actionRange;
    }

       //   Additional private method to get the monster troop
       //   in the lanceforged's range. Used for the takeAction method.
    private Monster[] nearestTroop() {
        Tile rangeTile = this.getPosition();
        for (int i = 0; i <= this.actionRange; i ++) {
            if (rangeTile.getNumOfMonsters() != 0 && rangeTile.isOnThePath() && rangeTile.isCamp() == false) {
                return rangeTile.getMonsters();
            }
            rangeTile = rangeTile.towardTheCamp();
        }
        return null;
    }
    public int takeAction() {
        double skillPoints = 0;
        Monster[] nearestMonsterTroop = this.nearestTroop();
        if (nearestMonsterTroop != null) {
            int n;
               //   Verifies which is greater between the number of monsters ans the lanceforged's piercing power.
            if (this.piercingPower <= nearestMonsterTroop.length) {
                n = this.piercingPower;
            } else {
                n = nearestMonsterTroop.length;
            }
               //   Applies damage for all monster according to n.
            for (int i = 0; i < n; i++) {
                double damageInduced = nearestMonsterTroop[i].takeDamage((double) BASE_ATTACK_DAMAGE, WEAPON_TYPE);
                skillPoints += 1 + (BASE_ATTACK_DAMAGE / damageInduced);
            }
            skillPoints /= n;
        }
        return (int) skillPoints;
    }
}