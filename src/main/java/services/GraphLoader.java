package services;
import com.opencsv.CSVReader;
import models.Graph;
import models.Station;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
public class GraphLoader {
    public static Graph loadGraph() {
        String[] lineArray;
        Graph graph = null;
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
            graph = new Graph((int) lines);
            // try read line by line, add to new station model and add to list of station nodes
            for (int i = 0; i < lines; i++) {
                HashMap<String, Integer> neighbours = new HashMap<>();
                lineArray = csvReader.readNext();
                String[] neighbourString = lineArray[2].split(";");
                for (String n : neighbourString) {
                    String[] valuePair = n.split("!");
                    neighbours.put(valuePair[0], Integer.parseInt(valuePair[1]));
                }
                String[] stationLines = lineArray[3].split(";");
                // adds node to the graph, and sets edges.
                graph.addNode(new Station(lineArray[0], lineArray[1], neighbours, i,
                        stationLines));
                // has to be effectively final to be used in lambda expression
                // needs to iterate through each kvp of current neighbours hashmap and add new edge.
                        Graph finalGraph = graph;
                int finalI = i;
                neighbours.forEach( (k, v) -> finalGraph.addEdge(finalI, (((int)k.charAt(0))-
                        65),v));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }
}