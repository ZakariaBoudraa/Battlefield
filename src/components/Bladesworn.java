package components;

public class Bladesworn extends Warrior {
    public static double BASE_HEALTH;
    public static int BASE_COST;
    public static int WEAPON_TYPE = 3;
    public static int BASE_ATTACK_DAMAGE;

    public Bladesworn (Tile position) {
        super(position, BASE_HEALTH, WEAPON_TYPE, BASE_ATTACK_DAMAGE, BASE_COST);
    }

    public int takeAction() {
        double skillPoints = 0;
        Tile tileOfBladesworn = this.getPosition();
        Monster firstMonsterOnTile = this.getPosition().getMonster();
           //   Checks if a monster is on the tile of the bladesworn.
        if (firstMonsterOnTile != null) {
            double damageInduced = firstMonsterOnTile.takeDamage((double) BASE_ATTACK_DAMAGE, WEAPON_TYPE);
            skillPoints = 1 + (BASE_ATTACK_DAMAGE / damageInduced);
        } else {
               //   Moves the bladesworn by one tile if no monster is found.
            if (this.getPosition().isCamp() == false && this.getPosition().getNumOfMonsters() == 0) {
                tileOfBladesworn.removeFighter(this);
                tileOfBladesworn.towardTheCamp().addFighter(this);
            }
        }
        return (int) skillPoints;
    }
}
