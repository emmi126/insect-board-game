public class AngryBee extends HoneyBee {

    private int attackDamage;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public AngryBee(Tile tile, int attackDamage) {
        super(tile, BASE_HEALTH, BASE_COST);
        this.attackDamage = attackDamage;
    }

    public boolean takeAction() {
        Tile currTile = getPosition();
        if (currTile == null || !currTile.isOnThePath()) return false;
        Tile nextTile = currTile.towardTheNest();

        if (currTile.getNumOfHornets() > 0 && !currTile.isNest()) {
            currTile.getHornet().takeDamage(attackDamage);
            return true;
        } else if (nextTile != null && nextTile.getNumOfHornets() > 0 && !nextTile.isNest()) {
            nextTile.getHornet().takeDamage(attackDamage);
            return true;
        }
        return false;
    }
}
