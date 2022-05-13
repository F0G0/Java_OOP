
public class Product{

    private final String id;
    private final String name;

    public static class Stats {

        String name;
        int produced;
        int consumerCount;
        int storage_A;
        int storage_B;
        int to_create;
        int to_consume;
        int to_load;
        int to_unload;
        int weight;

        public Stats(String name, int produced, int storage_A, int storage_B, int to_create, int to_consume, int to_load, int to_unload, int weight) {
            this.name = name;
            this.produced = produced;
            this.storage_A = storage_A;
            this.storage_B = storage_B;
            this.to_create = to_create;
            this.to_consume = to_consume;
            this.to_load = to_load;
            this.to_unload = to_unload;
            this.weight = weight;
        }
    }

    public Product(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

}
