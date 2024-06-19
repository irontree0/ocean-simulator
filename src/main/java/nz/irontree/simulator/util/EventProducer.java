package nz.irontree.simulator.util;

import nz.irontree.simulator.model.Shark;

public class EventProducer {

    // 0 - 19 sleep +10 energy
    // 20 - 29 unsuccessful hunting -7 energy
    // 30 - 39 ate ray -3 energy, +coeff *4 health
    // 40 - 49 ate squid -4 energy, +coeff *4 health
    // 50 - 59 meat was spoiled, -10 health
    // 60 - 69 eat seaweed, +coeff *2 health
    // 70 - 84 do nothing, -3 energy
    // 85 - 89 found fisher boat full of fish, -10 energy, +coeff *5 health
    // 90 - 95 fight with orca -20 health
    // 96 - 100 washed ashore -50 health -50 energy
    // if energy = 0, -5 health

    public void startSimulator(Shark shark) throws InterruptedException {
        while (checkHealth(shark)) {
            Thread.sleep(500);
            int eventNumber = (int) (Math.random() * 100);
            if (eventNumber >= 0 && eventNumber < 20) {
                sleep(shark);
            } else if (eventNumber >= 20 && eventNumber < 30) {
                move(shark);
            } else if (eventNumber >= 30 && eventNumber < 40) {
                eatRay(shark);
            } else if (eventNumber >= 40 && eventNumber < 50) {
                eatSquid(shark);
            } else if (eventNumber >= 50 && eventNumber < 60) {
                poisonItself(shark);
            } else if (eventNumber >= 60 && eventNumber < 70) {
                eatSeaweed(shark);
            } else if (eventNumber >= 70 && eventNumber < 85) {
                doNothing(shark);
            } else if (eventNumber >= 85 && eventNumber < 90) {
                findBoat(shark);
            } else if (eventNumber >= 90 && eventNumber < 95) {
                orcaAttack(shark);
            } else if (eventNumber >= 95 && eventNumber <= 100) {
                washedAshore(shark);
            }
        }
        System.out.print("The health ran out! The Shark is dead. GAME OVER");
    }

    private void sleep(Shark shark) {
        int energy = shark.getEnergy();
        energy = Math.min(energy + 10, 100);
        shark.setEnergy(energy);
        checkEnergy(shark);
        System.out.println("The Shark has slept! +10 to Energy. Current Energy: " + shark.getEnergy());
    }

    private void move(Shark shark) {
        int energy = shark.getEnergy();
        energy = Math.max(energy - 7, 0);
        shark.setEnergy(energy);
        checkEnergy(shark);
        System.out.println("The Shark has swum a lot! -7 from Energy. Current Energy: " + shark.getEnergy());
    }

    private void eatRay(Shark shark) {
        int energy = shark.getEnergy();
        energy = Math.max(energy - 3, 0);
        shark.setEnergy(energy);
        int health = shark.getHealth();
        health = Math.min(health + (shark.getCoeffTeeth() * 4), 100);
        shark.setHealth(health);
        checkEnergy(shark);
        System.out.printf("The Shark has eaten a ray! -3 from Energy. Current Energy: %s. " +
                "Food gives a lot of health. Current Health: %s%n", shark.getEnergy(), shark.getHealth());
    }

    private void eatSquid(Shark shark) {
        int energy = shark.getEnergy();
        energy = Math.max(energy - 4, 0);
        shark.setEnergy(energy);
        int health = shark.getHealth();
        health = Math.min(health + (shark.getCoeffTeeth() * 4), 100);
        shark.setHealth(health);
        checkEnergy(shark);
        System.out.printf("The Shark has eaten a squid! -4 from Energy. Current Energy: %s. " +
                "Food gives a lot of health. Current Health: %s%n", shark.getEnergy(), shark.getHealth());
    }

    private void poisonItself(Shark shark) {
        int health = shark.getHealth();
        health = Math.min(health - 10, 100);
        shark.setHealth(health);
        checkEnergy(shark);
        System.out.println("The food was definitely bad! -10 from Health Current Health: " + shark.getHealth());
    }

    private void eatSeaweed(Shark shark) {
        int health = shark.getHealth();
        health = Math.min(health + (shark.getCoeffTeeth() * 2), 100);
        shark.setHealth(health);
        checkEnergy(shark);
        System.out.println("The Shark has eaten seaweed! Current Health: " + shark.getHealth());
    }

    private void doNothing(Shark shark) {
        int energy = shark.getEnergy();
        energy = Math.max(energy - 3, 0);
        shark.setEnergy(energy);
        checkEnergy(shark);
        System.out.println("There's nothing to do, so boring! -3 from Energy. Current Energy: " + shark.getEnergy());
    }

    private void findBoat(Shark shark) {
        int energy = shark.getEnergy();
        energy = Math.max(energy - 10, 0);
        shark.setEnergy(energy);
        int health = shark.getHealth();
        health = Math.min(health + (shark.getCoeffTeeth() * 5), 100);
        shark.setHealth(health);
        checkEnergy(shark);
        System.out.printf("The Shark has found a fishers' boat full of fish! It was hard, but what a luck! " +
                "-10 from Energy. Current Energy: %s. Current Health: %s%n", shark.getEnergy(), shark.getHealth());
    }

    private void orcaAttack(Shark shark) {
        int health = shark.getHealth();
        health = Math.max(health - 20, 0);
        shark.setHealth(health);
        checkEnergy(shark);
        System.out.println("The Shark was attacked by the orca! -20 from Health. Current Health: " + shark.getHealth());
    }

    private void washedAshore(Shark shark) {
        int health = shark.getHealth();
        health = Math.max(health - 50, 0);
        shark.setHealth(health);
        int energy = shark.getEnergy();
        energy = Math.max(energy - 50, 0);
        shark.setEnergy(energy);
        checkEnergy(shark);
        System.out.printf("The Shark washed up on shore! It tries to go back. -50 from Energy. " +
                "Current Energy: %s. -50 from Health. Current Health: %s%n", shark.getEnergy(), shark.getHealth());
    }

    private boolean checkHealth(Shark shark) {
        if (shark.getHealth() <= 0 ) {
            return false;
        } else return true;
    }

    private void checkEnergy(Shark shark) {
        if (shark.getEnergy() <= 0) {
            int health = shark.getHealth() - 5;
            if (health < 0) {
                health = 0;
            }
            shark.setHealth(health);
        }
    }
}
