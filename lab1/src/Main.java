
public class Main {
    public static void main(String[] args){
        if (args.length != 2) {
            System.err.println("Wrong data format. Try: *.txt *.scv");
            return;
        }
        Converter csv = new Converter();
        csv.start(args[0],args[1]);

    }
}
