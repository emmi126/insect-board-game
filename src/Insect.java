public abstract class Insect {
    private Tile tile;
    private int hp;

    public Insect(Tile tile, int hp) {
        this.tile = tile;
        this.hp = hp;

        boolean insectAdded = this.tile.addInsect(this);
        if (!insectAdded) {
            throw new IllegalArgumentException("Insect was not added to tile!");
        }
    }

    final public Tile getPosition() {
        return tile;
    }

    final public int getHealth() {
        return hp;
    }

    public void setPosition(Tile tile) {
        this.tile = tile;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            tile.removeInsect(this);
        }
    }

    public abstract boolean takeAction();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Insect)) return false;
        Insect objInsect = (Insect) obj;
        return (this.getClass() == objInsect.getClass() && this.tile == objInsect.getPosition() && this.hp == objInsect.getHealth());
    }

    public void regenerateHealth(double percentRegen) {
        hp = (int) (hp * (1.0 + percentRegen));
    }
}
