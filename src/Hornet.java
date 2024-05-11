import static org.junit.jupiter.api.Assertions.assertEquals;

public class Hornet extends Insect {

    private int attackDamage;

    public static int BASE_FIRE_DMG;

    private boolean isQueen = false;
    private static int numQueens = 0;

    public Hornet(Tile tile, int hp, int attackDamage) {
        super(tile, hp);
        this.attackDamage = attackDamage;
    }

    public boolean takeAction() {
        Tile currTile = getPosition();
        if (currTile == null) return false;

        boolean actionTaken = false;
        boolean doubleAction = isTheQueen();
        int numOfActionsTaken = 0;

        if (currTile.isOnFire()) {
            takeDamage(BASE_FIRE_DMG);
            if (getHealth() <= 0) return false;
            if (doubleAction) takeDamage(BASE_FIRE_DMG);
            if (getHealth() <= 0) return false;
        }

        while ((!doubleAction && numOfActionsTaken < 1) || (doubleAction && numOfActionsTaken < 2)) {
            if (currTile.getBee() != null) {
                currTile.getBee().takeDamage(attackDamage);
                numOfActionsTaken++;
                if (doubleAction && numOfActionsTaken < 2) {
                    if (currTile.getBee() == null) return false;
                    currTile.getBee().takeDamage(attackDamage);
                    numOfActionsTaken++;
                }
                actionTaken = true;
            } else if (currTile.getBee() == null && currTile.isHive()) {
                actionTaken = false;
                break;
            } else {
                currTile.removeInsect(this);
                currTile = currTile.towardTheHive();
                currTile.addInsect(this);
                numOfActionsTaken++;
                actionTaken = true;
            }
        }

        return actionTaken;
    }

    @Override
    public boolean equals(Object obj) {
        boolean prevEquals = super.equals(obj);
        if (prevEquals) {
            if (!(obj instanceof Hornet)) return false;
            Hornet objHornet = (Hornet) obj;
            return (this.attackDamage == objHornet.attackDamage);
        }
        return false;
    }

    public boolean isTheQueen() {
        return isQueen;
    }

    public void promote() {
        if (numQueens == 0) {
            isQueen = true;
            numQueens++;        // FORGOT THIS LINE !!! – GRADING TEST CASE FAILED
        }
    }
}
