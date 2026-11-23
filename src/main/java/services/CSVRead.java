package services;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
public class CSVRead {
    public static ArrayList<String> readStationNames() {
        ArrayList<String> stations = new ArrayList<>();
        String[] lineArray;
        try {
            FileReader filereader = new FileReader("src/main/resources/stations.csv");
            CSVReader csvReader = new CSVReader(filereader);
            //count number of rows
            Path path = Paths.get("src/main/resources/stations.csv");
            long lines = 0;
            try {
                lines = Files.lines(path).count();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // try read line by line
            for (int i = 0; i < lines; i++) {
                lineArray = csvReader.readNext();
                stations.add(lineArray[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return stations;
    }
    public static HashMap<String,String> readStationNamesAndCodes() {
        HashMap<String,String> map = new HashMap<>();
        String[] lineArray = new String[2];
        try {
            FileReader filereader = new FileReader("src/main/resources/stations.csv");
            CSVReader csvReader = new CSVReader(filereader);
            //count number of rows
            Path path = Paths.get("src/main/resources/stations.csv");
            long lines = 0;
            try {
                lines = Files.lines(path).count();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // try read line by line
            for (int i = 0; i < lines; i++) {
                lineArray = csvReader.readNext();
                map.put(lineArray[0], lineArray[1]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
