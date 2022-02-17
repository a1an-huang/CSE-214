/**
 *  Graph : graph class creates a graph data strucute with an adjaceny matrix
 * 
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         December 2nd, 2021
 */
import java.util.ArrayList;
import java.util.Arrays;
public class Graph {
    private final double[][] adjacencyMatrix;
    private static final String s =
    "0.0 0.0 0.5 0.7 0.0 0.0\n" +
    "0.0 0.0 0.7 0.0 1.0 0.0\n" +
    "0.5 0.7 0.0 1.0 0.0 0.9\n" +
    "0.7 0.0 1.0 0.0 0.0 0.0\n" +
    "0.0 1.0 0.0 0.0 0.0 0.7\n" +
    "0.0 0.0 0.9 0.0 0.7 0.0";
    private Graph(double[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }
    /** 
     * convert matrix string into the adjaceny matrix
     */
    public static Graph fromMatrixString(String s) { 
        if(s.isEmpty()){
            return new Graph(new double[0][0]);
        }
        int newLine = s.indexOf("\n");
        int row = 1, col = 1;
        for(int i = 0; i < newLine; i ++){
            if(s.charAt(i) == ' '){
                row++;
                col++;
            }
        }
        double[][] matrix = new double[row][col];
        String current = "";
        for(int i = 0; i < row; i ++){
            if(newLine == -1){
                s += "\n";
                newLine = s.indexOf("\n");
            }
            current = s.substring(0, newLine) + " ";
            for(int j = 0; j < col; j++){
                if(Double.parseDouble(current.substring(0, current.indexOf(" "))) < 0){
                    throw new IllegalArgumentException("Inputs can not be NEGATIVE.");
                }
                matrix[i][j] = Double.parseDouble(current.substring(0, current.indexOf(" ")));
                current = current.substring(current.indexOf(" ") + 1);
            }
            s = s.substring(newLine + 1);
            newLine = s.indexOf("\n");
        }
        return new Graph(matrix);
    }   
    /**
     * matrix size = n * n 
     * n = length of one row
     * @return length of one row
     */
    /* TODO: */ 
    public Graph getTree(){
        double[][] matrix = new double[this.adjacencyMatrix.length][this.adjacencyMatrix.length];
        ArrayList<Integer> visited = new ArrayList<Integer>(Arrays.asList(0));
        while(visited.size() < this.adjacencyMatrix.length){
            double least = Double.POSITIVE_INFINITY;
            int curr = 0, next = 0;
            for(int i : visited){
                for(int j = 0; j < this.adjacencyMatrix.length; j++){
                    if(this.adjacencyMatrix[i][j] < least && this.adjacencyMatrix[i][j] > 0 && !visited.contains(j)){
                        least = this.adjacencyMatrix[i][j];
                        curr = i;
                        next = j;
                    }
                }
            };
            matrix[curr][next] = least;
            matrix[next][curr] = least;
            visited.add(next);
        }
        return new Graph(matrix);
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                builder.append(String.format("%1.2f ", adjacencyMatrix[i][j]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }
    public static void main(String... args) {
        // you can assume that incorrect strings will not be provided as input,
        // i.e., any input string will be a valid adjacency matrix
        // representation of an undirected weighted graph
        Graph g = Graph.fromMatrixString(s);
        System.out.println(g);
        System.out.println(g.getTree());
    }
 }
 