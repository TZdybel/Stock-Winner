import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVParser extends Parser {

    public CSVParser(){};

    @Override
    public void csvParsing(String filename, int key, int val){
        String line = "";
        String splitBy = ",";
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            line = br.readLine();
            while((line = br.readLine()) != null){
                String[] row = line.split(splitBy);
                super.values.put(row[key], Double.parseDouble(row[val]));
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

}
