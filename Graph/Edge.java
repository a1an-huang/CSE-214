import java.util.function.DoubleFunction;

public class Edge implements Comparable<Edge>{
    private final int u;
    private final int v;
    private final double weight;
    public Edge(int u, int v, double weight){
        if(u < 0 || v < 0 || weight < 0){
            throw new IllegalArgumentException();
        }
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
    public int one(){
        return u;
    }
    public int two(int x){
        if(x != u && x != v){
            throw new IllegalArgumentException();
        }
        return x == u ? v :u;
    }
    public int compareTo(Edge that){
        return Double.compare(this.weight, that.weight);
    }
    public String toString(){
        return String.format("%d--%f--%d", u , v, weight);
    }
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Edge))  {
            return false;
        }              
        Edge that = (Edge) o;
        return (this.u == that.u && this.v == that.v && this.weight == that.weight);
    }
}
