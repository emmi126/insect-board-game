public class SniperBee extends HoneyBee {
    private int attackDamage;
    private int piercingPower;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    private boolean isAiming;

    public SniperBee(Tile tile, int attackDamage, int piercingPower) {
        super(tile, BASE_HEALTH, BASE_COST);
        this.attackDamage = attackDamage;
        this.piercingPower = piercingPower;
        isAiming = true;
    }

    public boolean takeAction() {
        Tile currTile = getPosition();
        if (currTile == null || !currTile.isOnThePath()) return false;

        if (isAiming) {
            isAiming = false;
            return false;
        } else {
            isAiming = true;
            Tile observedTile = currTile;
            while (observedTile != null && !observedTile.isNest()) {
                if (observedTile.getNumOfHornets() > 0) {
                    Hornet[] hornets = observedTile.getHornets();
                    for (int i = 0; i < hornets.length && i < piercingPower; i++) {
                        hornets[i].takeDamage(attackDamage);
                    }
                    return true;
                } else {
                    observedTile = observedTile.towardTheNest();
                }
            }
        }

        return false;
    }
}
