package assignment1;

public class Monster extends Fighter {
    private int rageLevel;
    public static int BERSERK_THRESHOLD;

    public Monster(Tile position, double health, int weaponType, int attackDamage) {
        super(position, health ,weaponType, attackDamage);
        this.rageLevel = 0;
    }

    public int takeAction() {
        Tile tileOfMonster = this.getPosition();
        if (tileOfMonster != null) {                                                                                                           //********
            Warrior warriorOnTile = tileOfMonster.getWarrior();
               //   Checks if berserk threshold was reached.
            if (this.rageLevel >= BERSERK_THRESHOLD) {
                   //   Monster takes two consecutive actions in berserk mode.
                for (int i = 0; i < 2; i++) {
                       //   Checks if a warrior is on the same tile as the monster.
                    if (warriorOnTile != null) {
                        warriorOnTile.takeDamage((double) this.getAttackDamage(), this.getWeaponType());
                        Monster attackingMonster = tileOfMonster.getMonster();
                           //   Puts the monster at the end of the troop after taking action.
                        tileOfMonster.removeFighter(attackingMonster);
                        tileOfMonster.addFighter(attackingMonster);
                    } else {
                           //   Moves the monster by one tile if no warrior is found.
                        if (tileOfMonster.towardTheCastle() != null) {
                            tileOfMonster.removeFighter(this);
                            tileOfMonster.towardTheCastle().addFighter(this);
                        }
                    }
                }
                this.rageLevel = 0;
               //   Monster takes one action if not in berserk mode.
            } else {
                if (warriorOnTile != null) {
                    warriorOnTile.takeDamage((double) this.getAttackDamage(), this.getWeaponType());
                    Monster attackingMonster = tileOfMonster.getMonster();
                    tileOfMonster.removeFighter(attackingMonster);
                    tileOfMonster.addFighter(attackingMonster);
                } else {
                    if (tileOfMonster.towardTheCastle() != null) {
                        tileOfMonster.removeFighter(this);
                        tileOfMonster.towardTheCastle().addFighter(this);
                    }
                }
            }
        }
        return 0;
    }
    public boolean equals(Object o) {
           //   Takes the equals method from Fighter class to avoid repeated code.
        if (super.equals(o) == true && this.getAttackDamage() == ((Monster) o).getAttackDamage()) {
            return true;
        } else {
            return false;
        }
    }
    public double takeDamage(double damage, int opponentWeaponType) {
           //   Takes the takeDamage method from Fighter class to avoid repeated code.
        double damageReceived = super.takeDamage(damage, opponentWeaponType);
        if (this.getWeaponType() < opponentWeaponType && this.getHealth() > 0) {
            int rageGain = (opponentWeaponType - this.getWeaponType());
            this.rageLevel += rageGain;
           //   Resets the rage level if monster dies.
        } else if (this.getHealth() <= 0) {
            this.rageLevel = 0;
        }
        return damageReceived;
    }
}
