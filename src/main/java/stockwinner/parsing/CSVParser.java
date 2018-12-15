package stockwinner.parsing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVParser extends Parser {

    public CSVParser() {
    }

    ;

    @Override
    public void csvParsing(String filename, int key, int val) {
        String line = "";
        String splitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] row = line.split(splitBy);
                super.getValues().put(row[key], Double.parseDouble(row[val]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void csvAttributes(String filename) {
        String line = "";
        String splitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            line = br.readLine();
            String[] split = line.split(",");
            for(int i=0; i<split.length; i++){
                super.getAttributes().add(split[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}