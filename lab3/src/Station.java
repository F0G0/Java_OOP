
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Station {

    public static class Stats {
        int distance;
        int capacity_A;
        int capacity_B;
        int ways_A;
        int ways_B;
        Stats(int distance, int capacity_A, int capacity_B, int ways_A, int ways_B){
            this.distance = distance;
            this.capacity_A = capacity_A;
            this.capacity_B = capacity_B;
            this.ways_A = ways_A;
            this.ways_B = ways_B;
        }
    }

    private final int capacity;
    private int taken = 0;
    private final Boolean docking = false;


    private final BlockingQueue<Storage> storages_deque;
    private final Map<String, Storage> storages_map;

    public Station(int capacity, Map<String, Storage> storages_map){
        this.capacity = capacity;
        storages_deque = new LinkedBlockingQueue<>();
        this.storages_map = storages_map;
        for (var item : storages_map.entrySet()){
            storages_deque.add(item.getValue());
        }
    }

    public void dock() throws InterruptedException {
        synchronized (docking){
            while(taken == capacity){
                docking.wait();
            }
            ++taken;
        }
    }

    public void undock(){
        synchronized (docking) {
            if (taken == 0) {
                return;
            }
            --taken;
            docking.notify();
        }
    }

    public synchronized Storage getStorage() throws InterruptedException {
        if (storages_deque.isEmpty())
            return null;
        storages_deque.put(storages_deque.peek());
        return storages_deque.take();
    }

    public Storage getStorage(String productName) {
        return storages_map.get(productName);
    }

}
