
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.LinkedList;

public class Storage{

    private static final Logger logger = LogManager.getLogger(Storage.class);

    private static int count = 0;

    private final int id;
    private final LinkedList<Product> products;
    private final int capacity;
    private final String name_of_product;

    public Storage(String name_of_product, int capacity){
        products = new LinkedList<>();
        this.name_of_product = name_of_product;
        this.capacity = capacity;
        synchronized (Storage.class){
            this.id = ++count;
        }
    }

    public synchronized void put(Product item) throws InterruptedException {
        while (products.size() >= capacity)
            wait();
        products.add(item);
        logger.info("Storage: added " + this.name_of_product + id +". Weight:" + products.size());
        notify();
    }



    public synchronized Product get() throws InterruptedException {
        while (products.isEmpty()){
            wait();
        }
        Product item = products.getFirst();
        products.removeFirst();
        logger.info("Storage: taken " + this.name_of_product + id +". Amount: " + products.size());
        notify();
        return item;

    }

    public int getCapacity(){
        return capacity;
    }

    public String getName_of_product() {
        return name_of_product;
    }
}
