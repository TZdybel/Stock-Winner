package stockwinner.parsing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVParser extends Parser {


    @Override
    public void parseValues(String filename, String val) {
        parseValues(filename, getAttributes().indexOf(val));
    }

    @Override
    public void parseValues(String filename, int val) {
        super.getValues().clear();
        String line = "";
        String splitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] row = line.split(splitBy);
                super.getValues().put(row[0], Double.parseDouble(row[val]));
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
    public void parseAttributes(String filename) {
        super.getAttributes().clear();
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