package services;
import models.Graph ;
import java.util.ArrayList;
import java.util.Arrays;
import static services.CSVRead.readStationNames;
public class ShortestRouteService {
    public static String findShortestRoute(String origin, String destination, Graph graph) {
        ArrayList<String> listOfStations = readStationNames();
        Integer startIndex = listOfStations.indexOf(origin);
        Integer endIndex = listOfStations.indexOf(destination);
        Integer[][] adjMatrix = graph.getAdjMatrix();
        Integer[] unsettled = new Integer[graph.getNumOfNodes()];
        Integer[] settled = new Integer[graph.getNumOfNodes()];
        Integer numOfSettled = 0;
        String[] shortestPaths = new String[graph.getNumOfNodes()];
        Arrays.fill(unsettled, 99999);
        unsettled[startIndex] = 0;
        shortestPaths[startIndex] = Integer.toString(startIndex);
        Integer currentNode = startIndex;
        Integer currentPathWeight;
        Integer smallestIndex = currentNode;
        while (numOfSettled != unsettled.length){
            currentNode = smallestIndex;
            currentPathWeight = unsettled[currentNode];
            for (int i = 0; i < graph.getNumOfNodes(); i++) {
                if ((adjMatrix[currentNode][i] + currentPathWeight) < unsettled[i]){
                    unsettled[i] = adjMatrix[currentNode][i] + currentPathWeight;
                    shortestPaths[i] = shortestPaths[currentNode] + ";" + i;
                }
            }
            settled[currentNode] = unsettled[currentNode];
            numOfSettled++;
            Integer tempSmallest = 99999;
            for (int i = 0; i < unsettled.length; i++){
                if (unsettled[i] < tempSmallest && settled[i] == null){
                    smallestIndex = i;
                    tempSmallest = unsettled[i];
                }
            }
        }
        return shortestPaths[endIndex] + "!" + settled[endIndex];
    }
}