/**
 * @author Ritwik Banerjee
 */
public interface OpenAddressTable<V extends Hashable> extends Dictionary<V> {   
	public int hash(int key, int probenumber);
}


