
import java.util.HashMap;

public class Depot {

    private final Parser config;
    private final Station station_A;
    private final Station station_B;
    private final Railway railway;
    private final HashMap<String,Thread> trains;

    Depot(Parser config, Station station_A, Station station_B){
        trains = new HashMap<>();
        this.config = config;
        this.station_A = station_A;
        this.station_B = station_B;
        this.railway = new Railway(config.getStation_stats().distance, config.getStation_stats().ways_A, config.getStation_stats().ways_B);
    }

    public void newTrain(Train train) {
        Thread thread = new Thread(new Train(this, train.getConfig()));
        thread.start();
        synchronized (this){
            trains.put(train.getConfig().name, thread);
        }
    }

    public synchronized void createTrains() {
        for (var item : config.getTrain_stats().entrySet()){
            Thread thread = new Thread(new Train(this, item.getValue()));
            thread.start();
            trains.put(item.getKey(),thread);
        }
    }

    public void stop() {
        for (var thread : trains.entrySet()){
            thread.getValue().interrupt();
        }
        trains.clear();
    }

    public Station getStation_A() {
        return station_A;
    }

    public Station getStation_B() {
        return station_B;
    }

    public Railway getRailway() {
        return railway;
    }

    public Product.Stats getProductInfo(String name){
        return config.getProduct_stats().get(name);
    }

}
