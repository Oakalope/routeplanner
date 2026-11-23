package models;
import validators.ValidStation;
import validators.ValidTime;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static services.CSVRead.readStationNames;
import static services.CSVRead.readStationNamesAndCodes;
import static services.GraphLoader.loadGraph;
import static services.ShortestRouteService.findShortestRoute;
public class PlanModel {
    private HashMap<String, String> stationNamesAndCodes = readStationNamesAndCodes();
    private ArrayList<String> arrayOfStationNames = readStationNames();
    private Graph networkGraph = loadGraph();
    @ValidStation(message = "Please enter a station from the station list.")
    private String originStation;
    @ValidStation(message = "Please enter a station from the station list.")
    private String destinationStation;
    @ValidTime(message = "Please enter a valid time (HH:MM)")
    private String inputDepartureTime;
    private Integer departureTime;
    private Integer fastestTime;
    private ArrayList<String> route = new ArrayList<String>();
    private String[] routeIndexes;
    private Integer numOfConnections;
    private HashMap<String,String> connectingStations = new HashMap<>();
    public PlanModel() {
        departureTime = 0;
        originStation = "";
        destinationStation = "";
        inputDepartureTime = "";
        numOfConnections = 0;
        fastestTime = 0;
    }
    // setters
    public void setDestinationStation(@NotNull String destinationStation) {
        if (stationNamesAndCodes.containsValue(destinationStation.toUpperCase())) {
            for (Map.Entry<String, String> entry : stationNamesAndCodes.entrySet()) {
                if (Objects.equals(destinationStation.toUpperCase(), entry.getValue())) {
                    this.destinationStation = entry.getKey();
                }
            }
        }
        else{
            destinationStation = "INVALID";
        }
    }
    public void setOriginStation(@NotNull String originStation) {
        if (stationNamesAndCodes.containsValue(originStation.toUpperCase())) {
            for (Map.Entry<String, String> entry : stationNamesAndCodes.entrySet()) {
                if (Objects.equals(originStation.toUpperCase(), entry.getValue())) {
                    this.originStation = entry.getKey();
                }
            }
        }
        else{
            originStation = "INVALID";
        }
    }
    public void setInputDepartureTime(String inputDepartureTime) {
        this.inputDepartureTime = inputDepartureTime;
    }
// getters
public String getOriginStation() {
    return originStation;
}
    public String getDestinationStation() {
        return destinationStation;
    }
    public String getInputDepartureTime() {
        return inputDepartureTime;
    }
    public String getFastestTime() {
        return fastestTime.toString();
    }
    public String getRoute() {
        return route.toString();
    }
    public Integer getConnections() {
        return numOfConnections;
    }
    public HashMap<String, String> getStationNamesAndCodes() {
        return stationNamesAndCodes;
    }
    // methods
    public void calculateFastestRoute(){
        System.out.println("Calculating fastest route");
        System.out.println("Origin Station: " + originStation);
        System.out.println("Destination Station: " + destinationStation);
        System.out.println("Input Departure Time: " + inputDepartureTime);
        departureTime = Integer.parseInt(inputDepartureTime.split(":")[1]);
        System.out.println("Minutes past: " + departureTime);
        // gets string of fastest route
        String shortestRouteRaw =
                findShortestRoute(originStation,destinationStation,networkGraph);
        String routeIndexesString = shortestRouteRaw.split("!")[0];
        fastestTime = Integer.parseInt(shortestRouteRaw.split("!")[1]);
        routeIndexes = routeIndexesString.split(";");
        for (int i = 0; i < routeIndexes.length; i++) {
            route.add(arrayOfStationNames.get(Integer.parseInt(routeIndexes[i])));
        }
        // CURRENTLY ASSUMES TRAIN LEAVES DEPARTURE STATION EVERY HOUR
        fastestTime = fastestTime + (departureTime % 60);
    }
}
