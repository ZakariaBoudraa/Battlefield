package assignment1;

public class MonsterTroop {
    private Monster[] monsters;
    private int numOfMonsters;

    public MonsterTroop() {
           //   Creates an empty troop.
        this.numOfMonsters = 0;
        this.monsters = new Monster[0];       //IS THIS CORRECT ?????
    }
    public int sizeOfTroop() {
        return this.numOfMonsters;
    }
    public Monster[] getMonsters() {
        Monster[] monsterList = new Monster[this.numOfMonsters];
        for (int i = 0; i < monsterList.length; i++) {
            if (this.monsters[i] != null) {
                monsterList[i] = this.monsters[i];
            }
        }
        return monsterList;
    }
    public Monster getFirstMonster() {
        if (this.numOfMonsters == 0) {
            return null;
        } else {
            return this.monsters[0];
        }
    }
    public void addMonster(Monster m) {
           //   Checks if there is space in the troop for a new monster.
        if (this.monsters.length > this.numOfMonsters) {
            this.monsters[numOfMonsters] = m;
            numOfMonsters += 1;
        } else if (this.monsters.length == this.numOfMonsters) {
               //   Extends the monster troop if no place is available.
            Monster[] moreMonsters = new Monster[this.numOfMonsters + 1];
            for (int i = 0; i < this.numOfMonsters; i++) {
                moreMonsters[i] = this.monsters[i];
            }
            this.monsters = moreMonsters;
            this.monsters[numOfMonsters] = m;
            numOfMonsters += 1;
        }
    }
    public boolean removeMonster(Monster m) {
        for (int i = 0; i < this.monsters.length; i++) {
            if (this.monsters[i] == m) {
                this.monsters[i] = null;
                   //   Shifts all the monsters up in the troop after removing the first one.
                for (int k = i; k < this.monsters.length - 1; k++) {
                    this.monsters[k] = this.monsters[k + 1];
                }
                this.monsters[this.numOfMonsters - 1] = null;
                this.numOfMonsters -= 1;
                return true;
            }
        }
        return false;
    }
}
