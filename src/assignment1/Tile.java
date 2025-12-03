package assignment1;

public class Tile {
    private boolean isCastle;
    private boolean isCamp;
    private boolean onThePath;
    private Tile towardTheCastle;
    private Tile towardTheCamp;
    private Warrior warrior;
    private MonsterTroop troop;

    public Tile() {
        this.isCastle = false;
        this.isCamp = false;
        this.onThePath = false;
        this.towardTheCastle = null;
        this.towardTheCamp = null;
        this.warrior = null;
        this.troop = new MonsterTroop();
    }
    public Tile(boolean isCastle, boolean isCamp, boolean onThePath, Tile towardTheCastle, Tile towardTheCamp, Warrior warrior, MonsterTroop troop) {
        this.isCastle = isCastle;
        this.isCamp = isCamp;
        this.onThePath = onThePath;
        this.towardTheCastle = towardTheCastle;
        this.towardTheCamp = towardTheCamp;
        this.warrior = warrior;
        this.troop = troop;
    }
    public boolean isCastle() {
        if (isCastle == true) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isCamp() {
        if (isCamp == true) {
            return true;
        } else {
            return false;
        }
    }
    public void buildCastle() {
        this.isCastle = true;
    }
    public void buildCamp() {
        this.isCamp = true;
    }
    public boolean isOnThePath() {
        return this.onThePath;
    }
    public Tile towardTheCastle() {
        if (this.onThePath == false || this.isCastle == true) {
            return null;
        } else {
            return this.towardTheCastle;
        }
    }
    public Tile towardTheCamp() {
        if (this.onThePath == false || this.isCamp == true) {
            return null;
        } else {
            return this.towardTheCamp;
        }
    }
    public void createPath(Tile nextTileCastle, Tile nextTileCamp) {
        this.towardTheCastle = nextTileCastle;
        this.towardTheCamp = nextTileCamp;
        this.onThePath = true;
           //   Verifies if the method arguments are valid tiles.
        if (nextTileCastle == null) {
            if (this.isCastle == false) {
                throw new IllegalArgumentException("Current tile should have a next tile towards the castle");
            }
        } else if (nextTileCamp == null) {
            if (this.isCamp == false) {
                throw new IllegalArgumentException("Current tile should have a next tile towards the monster camp");
            }
        }
    }
    public int getNumOfMonsters() {
        int monstersOnTile = this.troop.sizeOfTroop();
        return monstersOnTile;
    }
    public Warrior getWarrior() {
        return this.warrior;
    }
    public Monster getMonster() {
           //   Returns first monster of troop on the given tile.
        Monster firstMonster = this.troop.getFirstMonster();
        return firstMonster;
    }
    public Monster[] getMonsters() {
        return this.troop.getMonsters();
    }
    public boolean addFighter(Fighter fighter) {
        if (fighter instanceof Warrior) {
               //   Checks if another warrior is on the tile, or if the tile is the camp.
            if (this.warrior == null && this.isCamp == false) {
                this.warrior = (Warrior) fighter;
                fighter.setPosition(this);
                return true;
            } else {
                return false;
            }
        } else if (fighter instanceof Monster) {
            if (this.onThePath == true || this.isCastle == true || this.isCamp == true) {
                this.troop.addMonster((Monster) fighter);
                fighter.setPosition(this);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public boolean removeFighter(Fighter fighter) {
        if (fighter instanceof Warrior) {
               //   Checks if a warrior is really on the tile.
            if (this.warrior == fighter) {
                this.warrior = null;
                fighter.setPosition(null);
                return true;
            } else {
                return false;
            }
        } else if (fighter instanceof Monster) {
            if (this.troop.removeMonster((Monster) fighter)) {
                fighter.setPosition(null);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
