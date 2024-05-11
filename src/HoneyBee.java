public abstract class HoneyBee extends Insect {

    private int cost;
    public static double HIVE_DMG_REDUCTION;

    public HoneyBee(Tile tile, int hp, int cost) {
        super(tile, hp);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public void takeDamage(int damage) {
        Tile currTile = getPosition();
        int actualDamage = damage;
        if (currTile.getBee() == this && currTile.isHive()) {
            actualDamage = (int) (damage * (1 - HIVE_DMG_REDUCTION));
        }
        super.takeDamage(actualDamage);
    }
}
