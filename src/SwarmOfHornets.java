public class SwarmOfHornets {

    private int numHornets;
    private Hornet[] hornetGroup;

    public static double QUEEN_BOOST;

    public SwarmOfHornets() {
        numHornets = 0;
        hornetGroup = new Hornet[0];
    }

    public int sizeOfSwarm() {
        return numHornets;
    }

    public Hornet[] getHornets() {
        Hornet[] hornetGroupCopy = new Hornet[numHornets];
        System.arraycopy(hornetGroup, 0, hornetGroupCopy, 0, numHornets);
        return hornetGroupCopy;
    }

    public Hornet getFirstHornet() {
        if (hornetGroup.length == 0) {
            return null;
        }
        return hornetGroup[0];
    }

    public void addHornet(Hornet hornet) {
        boolean queenBoost = hornet.isTheQueen();
        Hornet[] newGroup = new Hornet[hornetGroup.length + 1];
        for (int i = 0; i < hornetGroup.length; i++) {
            if (queenBoost) hornetGroup[i].regenerateHealth(QUEEN_BOOST);
            newGroup[i] = hornetGroup[i];
        }
        newGroup[newGroup.length - 1] = hornet;
        hornetGroup = newGroup;
        numHornets++;
    }

    public boolean removeHornet(Hornet hornet) {
        if (hornetGroup.length == 0) {
            return false;
        }
        Hornet[] newGroup = new Hornet[hornetGroup.length - 1];
        int i = 0;
        while (i < hornetGroup.length && hornet != hornetGroup[i]) {
            newGroup[i] = hornetGroup[i];
            i++;
        }
        if (i == hornetGroup.length) {
            return false;
        }
        for (int j = i; j < hornetGroup.length - 1; j++) {
            newGroup[j] = hornetGroup[j+1];
        }
        hornetGroup = newGroup;
        numHornets--;
        return true;
    }
}
