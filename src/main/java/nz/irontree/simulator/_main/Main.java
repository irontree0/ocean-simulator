package nz.irontree.simulator._main;

import nz.irontree.simulator.model.Shark;
import nz.irontree.simulator.util.EventProducer;

public class Main {
    // ocean simulator, animal - shark, figure out who to eat and  what to do
    public static void main(String[] args) throws InterruptedException {
        Shark shark = new Shark();
        EventProducer eventProducer = new EventProducer();
        eventProducer.startSimulator(shark);
    }
}