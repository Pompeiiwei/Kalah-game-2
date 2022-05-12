package containers;

public abstract class Container {
    protected int seedsCount;
    protected int seedsCountSimulation;

    public void sowSeeds(int seedsPerMove){
        seedsCount += seedsPerMove;
    }

    public void sowSeedsSimulation(int seedsPerMove){
        seedsCountSimulation += seedsPerMove;
    }

    public int getSeedsCount() {
        return seedsCount;
    }

    public int getSeedsCountSimulation(){
        return seedsCountSimulation;
    }

    public void setSeedsCountSimulation() {
        // make the simulation seeds number equal to real seeds number
        this.seedsCountSimulation = seedsCount;
    }

    public void setSeedsCount(int seedsCount) {
        this.seedsCount = seedsCount;
    }
}
