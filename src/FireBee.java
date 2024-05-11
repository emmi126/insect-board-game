public class FireBee extends HoneyBee {
    private int maxAttackRange;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public FireBee(Tile tile, int maxAttackRange) {
        super(tile, BASE_HEALTH, BASE_COST);
        this.maxAttackRange = maxAttackRange;
    }

    public boolean takeAction() {
        Tile currTile = getPosition();
        if (!currTile.isOnThePath()) return false;
        Tile nextTile = currTile.towardTheNest();
        boolean actionTaken = false;

        for (int i = 0; i < maxAttackRange && !nextTile.isNest() && !actionTaken; i++) {
            if (!nextTile.isOnFire() && nextTile.getNumOfHornets() > 0) {
                nextTile.setOnFire();
                actionTaken = true;
            } else {
                nextTile = nextTile.towardTheNest();
            }
        }
        return actionTaken;
    }
}
