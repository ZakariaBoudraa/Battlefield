package assignment1;

public abstract class Fighter {
    private Tile position;
    private double health;
    private int weaponType, attackDamage;

    public Fighter(Tile position, double health, int weaponType, int attackDamage) {
        this.position = position;
        this.health = health;
        this.weaponType = weaponType;
        this.attackDamage = attackDamage;
        boolean tileVerification = this.position.addFighter(this);
           //   Verify is the selected fighter can be added on the corresponding tile.
        if (tileVerification == false) {
            throw new IllegalArgumentException("Fighter could not be placed on this tile");
        }
    }

    public final Tile getPosition() {
        return this.position;
    }
    public final double getHealth() {
        return this.health;
    }
    public final int getWeaponType() {
        return this.weaponType;
    }
    public final int getAttackDamage() {
        return this.attackDamage;
    }
    public void setPosition(Tile newPosition) {
        this.position = newPosition;
    }
    public double takeDamage(double damage, int opponentWeaponType) {
           //   Check which fighter has the greatest weapon type.
        if (opponentWeaponType > this.weaponType) {
            damage *= 1.5;
        } else if (opponentWeaponType < this.weaponType) {
            damage *= 0.5;
        }
        this.health -= damage;
           //   Remove fighter from the game if it dies.
        if (this.health <= 0) {
            this.position.removeFighter(this);
        }
        return damage;
    }
    public abstract int takeAction();

    public boolean equals(Object o) {
           //   Checks for equality of certain aspects between fighters.
        if (o instanceof Fighter && this.position == ((Fighter) o).position && Math.abs(this.health - ((Fighter) o).health) <= 0.001) {
            return true;
        } else {
            return false;
        }
    }
}
