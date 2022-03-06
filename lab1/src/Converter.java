import java.io.*;
import java.util.*;

public class Converter {
    private List<Map.Entry<String, Integer>> word_list = new LinkedList<>();
    private int count = 0;

    private boolean readFile(String file_name){
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file_name));
            StringBuilder letters = new StringBuilder();
            Map<String, Integer> word_and_count = new HashMap<>();
            int tmp;
            while((tmp = reader.read())!=-1){
                if (Character.isLetterOrDigit(tmp)) {
                    letters.append((char) tmp);
                }
                else if (!letters.isEmpty()){
                    String word = letters.toString();
                    if (word_and_count.containsKey(word)){
                        word_and_count.put(word, word_and_count.get(word) + 1);
                    }
                    else {
                        word_and_count.put(word, 1);
                    }
                    count++;
                    letters.setLength(0);
                }
            }
            word_list.addAll(word_and_count.entrySet());
            word_list.sort((a, b) -> b.getValue() - a.getValue());
        }
        catch (IOException e){
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally
        {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
        return true;
    }

    private void writeFile(String file_name){
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(file_name));
            for (Map.Entry<String, Integer> pair : word_list) {
                writer.write(pair.getKey() + ";" + pair.getValue() + ";" + String.format("%.2f", (double) 100 * pair.getValue() / count) + System.lineSeparator());
            }
        }
        catch (IOException e)
        {
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
        }
        finally
        {
            if (null != writer)
            {
                try
                {
                    writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
    public void start(String in, String out){
        if (readFile(in)) writeFile(out);
    }
}
