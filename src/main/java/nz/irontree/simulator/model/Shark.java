package nz.irontree.simulator.model;

public class Shark {

    //100 points of health
    //100 points of energy
    //coefficient of teeth - 3 energy after eating

    private int health = 100;
    private int energy = 100;
    private final int COEFF_TEETH = 2;

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCOEFF_TEETH() {
        return COEFF_TEETH;
    }
}
