
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Factory implements Runnable{

    private static final Logger logger = LogManager.getLogger(Factory.class);

    private final int id;
    private final Storage storage;
    private final String name_of_product;
    private int product_count = 0;
    private final int delay;

    public Factory (int id, Product.Stats stats, Storage storage){

        this.id = id;

        name_of_product = stats.name;
        delay = stats.to_create;

        this.storage = storage;
    }


    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            try{
                Product item = new Product(name_of_product, newId());
                storage.put(item);
                logger.info("Factory " + this.id + ": Produced " + item.getName() + "; id: " + item.getId());
                Thread.sleep(delay);
            }
            catch (Exception e){
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private String newId(){
        return name_of_product + id + ++product_count;
    }
}
