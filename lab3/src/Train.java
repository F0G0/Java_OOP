
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.LinkedList;

public class Train implements Runnable{

    private static final Logger logger = LogManager.getLogger(Train.class);

    public Stats getConfig() {
        return stats;
    }

    public static class Stats {
        String name;
        int capacity;
        int speed;
        int to_create;
        int max_distance;

        Stats(String name, int capacity, int speed, int to_create, int max_distance){
            this.name = name;
            this.capacity = capacity;
            this.speed = speed;
            this.to_create = to_create;
            this.max_distance = max_distance;
        }

    }

    private final Stats stats;
    private final String name;

    private final int capacity;
    private final int speed;
    private int amortization;

    private final Depot depot;
    private final LinkedList<Product> products;
    private Product.Stats product_info;

    public Train(Depot depot, Stats stats){
        this.depot = depot;
        int roadLength = depot.getRailway().getLength();

        this.stats = stats;
        name = stats.name;
        capacity = stats.capacity;
        speed = stats.speed;
        amortization = stats.max_distance - roadLength;
        if (this.amortization < 0)
            this.amortization = 0;

        products = new LinkedList<>();
    }

    private void load(Storage storage) throws InterruptedException {
        int productCapacity = product_info.weight;
        for (int i = 0; i < capacity; i+=productCapacity) {
            products.addLast(storage.get());
            Thread.sleep( product_info.to_load);
        }
    }
    private void unload(Storage storage) throws InterruptedException {
        while (!products.isEmpty()) {
            storage.put(products.removeFirst());
            Thread.sleep( product_info.to_unload);
        }
    }

    @Override
    public void run() {
        logger.info("Train " + this.name + " is ready.");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                checkAmortization();
                if (Thread.currentThread().isInterrupted())
                    return;
                Storage storage = depot.getStation_A().getStorage();
                String productName = storage.getName_of_product();
                product_info = depot.getProductInfo(productName);
                logger.info("Train " + name + ": storage of " + productName + " is going to be taken.");

                depot.getStation_A().dock();
                logger.info("Train " + name + ": station A docked");

                load(storage);
                depot.getStation_A().undock();
                logger.info("Train " + name + ": station A undocked");

                Road road = depot.getRailway().getRoad_A_to_B();
                travel(road);
                depot.getRailway().freeRoad_A_to_B(road);
                logger.info("Train " + name + ": went to station B");

                depot.getStation_B().dock();
                logger.info("Train " + name + ": station B docked");

                unload(depot.getStation_B().getStorage(productName));
                depot.getStation_B().undock();
                logger.info("Train " + name + ": station B undocked");

                checkAmortization();
                if (Thread.currentThread().isInterrupted())
                    return;
                road = depot.getRailway().getRoad_B_to_A();
                travel(road);
                depot.getRailway().freeRoad_B_to_A(road);
                logger.info("Train " + name + ": went to station A");


            } catch (InterruptedException e) {
                return;
            }
        }
    }
    private void checkAmortization(){
        if (amortization < 0) {
            depot.newTrain(this);
            Thread.currentThread().interrupt();
        }
        else
            logger.info("Train " + name + ": left to ride = " + amortization);

    }

    private void travel(Road road) throws InterruptedException {
        logger.info("Train " + name + ": leaving");

        Thread.sleep( (int)(1000 * Math.ceil((double)road.getLength() /speed)));
        amortization -= road.getLength();

        logger.info("Train " + name + ": entering");
    }
}

