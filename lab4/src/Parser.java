import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Parser {

    private final HashMap<String, Product.Stats> product_stats;
    private final HashMap<String, Train.Stats> train_stats;
    private Station.Stats station_stats;

    Parser(){
        product_stats = new HashMap<>();
        train_stats = new HashMap<>();
    }

    public void parse(InputStream stream) throws IOException, ParseException {

        if (stream == null)
            throw new IOException();

        JSONObject conf = (JSONObject) new JSONParser().parse(new InputStreamReader(stream));
        JSONArray Product = (JSONArray) conf.get("Product");
        JSONArray Train = (JSONArray) conf.get("Train");
        JSONObject Station = (JSONObject) conf.get("Station");



        for (Object instance : Product){
            JSONObject productRecord = (JSONObject) instance;
            String name = (String)productRecord.get("name");
            int produced = ((Long) productRecord.get("produced")).intValue();
            int storage_A = ((Long) productRecord.get("storage_A")).intValue();
            int storage_B = ((Long) productRecord.get("storage_B")).intValue();
            int to_create = ((Long) productRecord.get("to_create")).intValue();
            int to_consume = ((Long) productRecord.get("to_consume")).intValue();
            int to_load = ((Long) productRecord.get("to_load")).intValue();
            int to_unload = ((Long) productRecord.get("to_unload")).intValue();
            int weight = ((Long) productRecord.get("weight")).intValue();
            product_stats.put(name,
                    new Product.Stats(name, produced,
                            storage_A, storage_B,
                            to_create, to_consume, to_load, to_unload, weight
                    ));

        }

        int distance = ((Long) Station.get("distance")).intValue();
        int capacity_A = ((Long) Station.get("capacity_A")).intValue();
        int capacity_B = ((Long) Station.get("capacity_B")).intValue();
        int ways_A = ((Long) Station.get("ways_A")).intValue();
        int ways_B = ((Long) Station.get("ways_B")).intValue();

        station_stats = new Station.Stats(distance, capacity_A, capacity_B, ways_A, ways_B);

        for (Object instance : Train){
            JSONObject productRecord = (JSONObject) instance;
            String name = (String)productRecord.get("name");
            int capacity = ((Long) productRecord.get("capacity")).intValue();
            int to_create = ((Long) productRecord.get("to_create")).intValue();
            int speed = ((Long) productRecord.get("speed")).intValue();
            int max_distance = ((Long) productRecord.get("max_distance")).intValue();
            train_stats.put(name,
                    new Train.Stats(name, capacity, speed,
                            to_create, max_distance
                    ));

        }

    }

    HashMap<String, Product.Stats> getProduct_stats(){
        return product_stats;
    }

    HashMap<String, Train.Stats> getTrain_stats(){
        return train_stats;
    }

    Station.Stats getStation_stats(){
        return station_stats;
    }

}
