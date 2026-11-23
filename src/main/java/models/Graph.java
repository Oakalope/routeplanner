package models;
import java.util.ArrayList;
public class Graph {
    Integer[][] adjMatrix;
    ArrayList<Station> nodeList = new ArrayList<>();
    Integer numOfNodes;
    public Graph(int numOfNodes){
        this.numOfNodes = numOfNodes;
        adjMatrix = new Integer[numOfNodes][numOfNodes];
        // all empty edges are of an arbitrarily large value
        for (int i = 0; i < numOfNodes; i++) {
            for (int j = 0; j < numOfNodes; j++) {
                adjMatrix[i][j] = 99999;
            }
        }
    }
    public void addNode(Station node){
        nodeList.add(node);
    }
    public void addEdge(Integer source, Integer destination, Integer weight){
        adjMatrix[source][destination] = weight;
    }
    public void print(){
        for (int i = 0; i < numOfNodes; i++) {
            for (int j = 0; j < numOfNodes; j++) {
                System.out.print(adjMatrix[i][j] + " | ");
            }
            System.out.println();
        }
    }
    public Integer[][] getAdjMatrix(){
        return adjMatrix;
    }
    public Integer getNumOfNodes(){
        return numOfNodes;
    }
    public Station getNode(int i){
        return nodeList.get(i);
    }
}
