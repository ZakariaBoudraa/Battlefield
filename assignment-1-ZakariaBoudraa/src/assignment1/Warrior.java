package assignment1;

public abstract class Warrior extends Fighter {
    private int requiredSkillPoints;
    public static double CASTLE_DMG_REDUCTION;

    public Warrior(Tile position, double health, int weaponType, int attackDamage, int requiredSkillPoints) {
        super(position, health, weaponType, attackDamage);
        this.requiredSkillPoints = requiredSkillPoints;
    }

    public int getTrainingCost() {
        return this.requiredSkillPoints;
    }
    public double takeDamage(double damage, int opponentWeaponType) {
        double damageReceived = 0;
        if (this.getPosition() != null) {
            if (this.getPosition().isCastle()) {
                   //   Reduces damage is the warrior is on the castle tile
                damage = damage - damage * CASTLE_DMG_REDUCTION;
                damageReceived += super.takeDamage(damage, opponentWeaponType);
            } else {
                damageReceived += super.takeDamage(damage, opponentWeaponType);
            }
        }
        return damageReceived;
    }
}
