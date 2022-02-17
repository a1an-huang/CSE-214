/**
 *  DoubleHashTable Class: Uses key of inputted object to add, delete, find in hashtable data structure.
 * 
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         November 28, 2021
 */
import java.util.function.Function;
import java.util.ArrayList;
public class DoubleHashTable<V extends Hashable> implements OpenAddressTable<V>{
    private Function<Integer, Integer> h1;
    private Function<Integer, Integer> h2;
    private ArrayList<Node<V>> table;
    public static void main(String[] args) {
        
        // your implementation of the h1 function described above
        Function<Integer, Integer> h1 = new Function<Integer, Integer>(){  
            public Integer apply(Integer value){ 
                String str = String.valueOf(value & 0x7FFFFFFF);
                return Integer.parseInt(str.substring(0,1));
            }
        };
        // your implementation of the h2 function described above
        Function<Integer, Integer> h2 = new Function<Integer, Integer>(){  
            public Integer apply(Integer value){ 
                String str = String.valueOf(value & 0x7FFFFFFF);
                return Integer.parseInt(str.substring(str.length() - 1)); 
            }
        };
        
        /** 
        Function<Integer, Integer> h1 = new Function<Integer, Integer>(){  
            public Integer apply(Integer value){ 
                return value;
            }
        };
        Function<Integer, Integer> h2 = new Function<Integer, Integer>(){  
            public Integer apply(Integer value){    
                return value;
            }
        };
        */
        //DoubleHashTable<HashableString> t = new DoubleHashTable<>(10, h1, h2);
        DoubleHashTable<HashableString> t = new DoubleHashTable<>(5, h1, h2);
        Hashable apple = new HashableString("apple");
        Hashable orange = new HashableString("orange");
        Hashable banana = new HashableString("banana");

        //test
        //takes a while for everything to load
        System.out.println(t.isEmpty());
        t.insert(apple);
        System.out.println(t);
        t.insert(orange);
        System.out.println(t);
        t.insert(banana);
        System.out.println(t);
        System.out.println(t.delete(apple));
        System.out.println(t);
        System.out.println(t.delete(banana));
        System.out.println(t);
        System.out.println(t.find(banana));
        System.out.println(t);
        System.out.println(t.find(orange));
        System.out.println(t.isEmpty());
        System.out.println(t.delete(orange));
        System.out.println(t);
        System.out.println(t.delete(banana));
        System.out.println(t.isEmpty());
        t.insert(apple);
        System.out.println(t);
    }
    public DoubleHashTable(){
    }
    public DoubleHashTable(int size, Function<Integer, Integer> h1, Function<Integer, Integer> h2){
        this.table = create(size);
        this.h1 = h1;
        this.h2 = h2;
        
    }
    public ArrayList <Node<V>> create(int size){
        if(size <= 0){
            throw new IllegalArgumentException("Table Size Can not be 0 or less");
        }
        ArrayList<Node<V>> table = new ArrayList<Node<V>>();
        for(int i = 0; i < size; i++){
            table.add(null);
        };
        return table;
    }
    @Override
    public boolean isEmpty(){
        for(int i = 0; i < table.size(); i++){
            if(table.get(i) != null && table.get(i).getValue() != null){
                return false;
            }
        }
        return true;
    }
    public boolean isFull(){
        for(int i = 0; i < table.size(); i++){
            if(table.get(i) == null || table.get(i).getValue() == null){
                return false;
            }
        }
        return true;
    }
    /** public void doubleList(){
        ArrayList<Node<V>> temp = create(table.size() * 2);
        for(int i = 0; i < table.size(); i++){
            insert(table.get(i).getValue());
        }
    }
    */
    @Override
    public void insert(Hashable value){
        if(value == null){
            throw new NullPointerException("Can not insert a null value");
        }
        if(isFull()){
            throw new IllegalArgumentException("Table is full can not insert");
        }
        int pos = hash(value.key(), table.size());
        System.out.println("pos" + pos);
        Node<V> newNode = new Node<V>(value , value.key() & 0x7FFFFFFF);
        if(table.get(pos) == null || table.get(pos).getValue() == null){
            table.set(pos, newNode);
        }else{
            while(table.get(pos) != null && table.get(pos).getValue() != null){
                pos = rehash(pos, value.key(), table.size());
            }
            if(find(value) != -1 || table.get(pos) == null){
                table.set(pos, newNode);
            }else{
                System.out.println("Can not insert duplicate keys");
            }
        }
    }
    @Override
    public Hashable delete(Hashable value){
        if(value == null){
            throw new NullPointerException("Can not delete a null value");
        }
        if(isEmpty()){
            return null;
        }
        Hashable temp = value;
        int pos = find(value);
        if(pos != -1){
            table.get(pos).setValue(null);
            table.get(pos).setKey(-1);
        }else{
            return null;
        }
        return temp;
    }
    @Override
    public int find(Hashable value){
        if(value == null){
            throw new NullPointerException("Can not find a null value");
        }
        int pos = hash(value.key(), table.size());
        if(table.get(pos).getValue() == value){
            return pos;
        }else{
            while(table.get(pos) != null){
                if(table.get(pos).getKey() == -1 || table.get(pos).getValue() != value){
                    pos = rehash(pos, value.key(), table.size());
                }else if(table.get(pos).getValue() == value){
                    return pos;
                }
            }
            System.out.println(pos);
        }
        return -1;
    }
    @Override
    public int hash(int key, int probenumber){
        return h1.apply(key) % probenumber;
    }
    public int rehash(int pos, int key, int probenumber){
        return (pos + (h2.apply(key) % probenumber)) % probenumber;
    }
    public String toString(){
        String str = "";
        for(int i = 0; i < table.size(); i++){
            if(table.get(i) != null){
                str += i + " -> [" + (table.get(i).getKey()) + ", " + table.get(i).getValue() + "]; ";
            }else{
                str += i + " -> null; ";
            }
        }
        return str;
    }
}
