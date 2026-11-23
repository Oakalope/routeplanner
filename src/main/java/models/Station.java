package models;
import java.util.HashMap;
public class Station {
    private String stationName;
    private Integer IDNumber;
    private String stationID;
    private HashMap<String, Integer> neighbours = new HashMap<String, Integer>();
    private String[] lines;
    private HashMap<String,Integer> departureTimes = new HashMap<String,Integer>();
    public Station(String stationName, String stationID, HashMap<String, Integer> neighbours,
                   Integer IDNumber,
                   String[] lines) {
        this.stationName = stationName;
        this.stationID = stationID;
        this.neighbours = neighbours;
        this.IDNumber = IDNumber;
        this.lines = lines;
    }
    // getters
    public HashMap<String, Integer> getNeighbours() {
        return neighbours;
    }
    public String getStationID() {
        return stationID;
    }
    public String getStationName() {
        return stationName;
    }
    public String[] getLines() {
        return lines;
    }
    // setters
    public void addNewDepartureTime(String line, Integer time){
        departureTimes.put(line,time);
    }}