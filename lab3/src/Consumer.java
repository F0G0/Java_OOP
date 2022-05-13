
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Consumer implements Runnable{

    private static final Logger logger = LogManager.getLogger(Consumer.class);

    private final int id;
    private final Storage storage;
    private final int delay;

    public Consumer(int id, Product.Stats stats, Storage storage){

        this.id = id;
        this.delay = stats.to_consume;
        this.storage = storage;

    }

    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            try{
                Product item = storage.get();
                logger.info("Consumer "+this.id + ": Getting " + item.getName() + "; id: " + item.getId());
                Thread.sleep(delay);
            }
            catch (Exception e){
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

}
