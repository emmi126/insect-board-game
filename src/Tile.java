public class Tile {

    private int food;
    private boolean hasBeeHive;
    private boolean hashornetNest;
    private boolean onPath;

    private Tile nextToHive;
    private Tile nextToNest;

    private HoneyBee bee;
    private SwarmOfHornets hornets;

    private boolean onFire = false;

    public Tile() {
        this.hasBeeHive = false;
        this.hashornetNest = false;
        this.onPath = false;
        this.food = 0;
        this.bee = null;
        this.hornets = new SwarmOfHornets();
    }

    public Tile(int food, boolean beeHiveBuilt, boolean hornetNestBuilt, boolean onPath, Tile nextToHive, Tile nextToNest, HoneyBee honeybee, SwarmOfHornets hornets) {
        this.hasBeeHive = beeHiveBuilt;
        this.hashornetNest = hornetNestBuilt;
        this.onPath = onPath;
        this.nextToHive = nextToHive;
        this.nextToNest = nextToNest;
        this.food = food;
        this.bee = honeybee;
        this.hornets = hornets;
    }

    public boolean isHive() {
        return hasBeeHive;
    }

    public boolean isNest() {
        return hashornetNest;
    }

    public void buildHive() {
        hasBeeHive = true;
    }

    public void buildNest() {
        hashornetNest = true;
    }

    public boolean isOnThePath() {
        return onPath;
    }

    public Tile towardTheHive() {
        if (!isOnThePath() || isHive()) {
            return null;
        }
        return nextToHive;
    }

    public Tile towardTheNest() {
        if (!isOnThePath() || isNest()) {
            return null;
        }
        return nextToNest;
    }

    public void createPath(Tile nextToHive, Tile nextToNest) {
        if ((nextToHive == null && !isHive()) || (nextToNest == null && !isNest())) {
            String errMsg = "Missing one of the input tiles.";
            throw new IllegalArgumentException(errMsg);
        } else if (nextToHive == null && isHive()) {
            this.nextToNest = nextToNest;
        } else if (nextToNest == null && isNest()) {
            this.nextToHive = nextToHive;
        } else {
            this.nextToHive = nextToHive;
            this.nextToNest = nextToNest;
        }
        onPath = true;
    }

    public int collectFood() {
        int amount = food;
        food = 0;
        return amount;
    }

    public void storeFood(int moreFood) {
        food += moreFood;
    }

    public int getNumOfHornets() {
        return hornets.sizeOfSwarm();
    }

    public HoneyBee getBee() {
        return bee;
    }

    public Hornet getHornet() {
        return hornets.getFirstHornet();
    }

    public Hornet[] getHornets() {
        return hornets.getHornets();
    }

    public boolean addInsect(Insect insect) {
        if (insect instanceof HoneyBee && bee == null && !isNest()) {
            insect.setPosition(this);
            bee = (HoneyBee) insect;
            return true;
        } else if (insect instanceof Hornet && isOnThePath()) {
            if (hornets == null) {
                hornets = new SwarmOfHornets();
            }
            insect.setPosition(this);
            hornets.addHornet((Hornet) insect);
            return true;
        }
        return false;
    }

    public boolean removeInsect(Insect insect) {
        if (insect instanceof HoneyBee && bee == insect) {
            insect.setPosition(null);
            bee = null;
            return true;
        } else if (insect instanceof Hornet) {
            boolean removed = hornets.removeHornet((Hornet) insect);
            if (removed) insect.setPosition(null);
            return removed;
        }
        return false;
    }

    public void setOnFire() {
        onFire = true;
    }

    public boolean isOnFire() {
        return onFire;
    }
}
