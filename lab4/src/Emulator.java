import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;


public class Emulator {

    Parser config;
    Station station_A;
    Station station_B;
    Depot depot;
    Map<String, Storage> storage_A = new HashMap<>();
    Map<String, Storage> storage_B = new HashMap<>();

    private final LinkedList<Thread> factories = new LinkedList<>();
    private final LinkedList<Thread> consumers = new LinkedList<>();

    public Emulator(InputStream configStream) throws IOException, ParseException {


        config = new Parser();
        config.parse(configStream);

        createStorages();
        createDepot();

    }


    public void start(){
        try{

            createThreads();
            depot.createTrains();

            Scanner in = new Scanner(System.in);
            while(true) {
                String cmd = in.nextLine();
                if (cmd.equals("exit")) {
                    terminate();
                    break;
                }
            }
            in.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void terminate() {
        depot.stop();
        for (var thread : factories){
            thread.interrupt();
        }
        factories.clear();
        for (var thread : consumers){
            thread.interrupt();
        }
        consumers.clear();
    }

    private void createStorages(){
        for (var item : config.getProduct_stats().entrySet()){
            Storage AStorage = new Storage(item.getKey(), item.getValue().storage_A);
            Storage BStorage = new Storage(item.getKey(), item.getValue().storage_B);
            storage_A.put(item.getKey(), AStorage);
            storage_B.put(item.getKey(), BStorage);
        }
    }

    private void createDepot() {
        station_A = new Station(config.getStation_stats().capacity_A, storage_A);
        station_B = new Station(config.getStation_stats().capacity_B, storage_B);
        depot = new Depot(config, station_A, station_B);

    }

    private void createThreads(){
        for (var item : config.getProduct_stats().entrySet()) {
            String productName = item.getValue().name;
            for (int i = 0; i < item.getValue().produced; ++i) {
                Thread thread = new Thread(new Factory(i, item.getValue(), storage_A.get(productName)));
                thread.start();
                factories.add(thread);
            }
            for (int i = 0; i < item.getValue().consumerCount; ++i) {
                Thread thread = new Thread(new Consumer(i, item.getValue(), storage_B.get(productName)));
                thread.start();
                consumers.add(thread);
            }
        }
    }

}
