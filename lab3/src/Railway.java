
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Railway{


    private static final Logger logger = LogManager.getLogger(Railway.class);

    private final BlockingDeque<Road> roads_A_to_B = new LinkedBlockingDeque<>();
    private final BlockingDeque<Road> roads_B_to_A = new LinkedBlockingDeque<>();


    private final int length;

    private void openRoads(int len, int waysA, int waysB){
        for (int i = 0; i < waysA; ++i){
            roads_A_to_B.add(new Road(len));
        }
        for (int i = 0; i < waysB; ++i){
            roads_B_to_A.add(new Road(len));
        }
    }

    public Railway(int len, int waysA, int waysB){
        openRoads(len, waysA, waysB);
        this.length = len;
    }

    public Road getRoad_A_to_B() throws InterruptedException {
        Road r = roads_A_to_B.take();
        logger.info("Railway: road from A to B is taken. Current roads: " + roads_A_to_B.size());
        return r;
    }
    public Road getRoad_B_to_A() throws InterruptedException {
        Road r = roads_B_to_A.take();
        logger.info("Railway: road from B to A is taken. Current roads: " + roads_B_to_A.size());
        return r;
    }

    public void freeRoad_A_to_B(Road road) throws InterruptedException {
        roads_A_to_B.put(road);
        logger.info("Railway: road from A to B is open. Current roads: " + roads_A_to_B.size());
    }
    public void freeRoad_B_to_A(Road road) throws InterruptedException {
        roads_B_to_A.put(road);
        logger.info("Railway: road from B to A is open. Current roads: " + roads_B_to_A.size());
    }

    public int getLength() {
        return length;
    }
}
