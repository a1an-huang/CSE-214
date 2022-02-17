/**
 *  Hashable Class: provided by Banerjee
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         November 28, 2021
 */
/**
* The <code>Hashable</code> interface provides the basic structure for any
* data type that can be hashed. This means that any instance of the data type
* should be interpretable as an integer. The <code>key()</code> method of
* this interface achieves this. For example, for a <code>MyClass</code> data
* type, the key of an instance <code>m</code> should be an integer value 
* obtained  by calling <code>m.key()</code>.
* 
* @author Ritwik Banerjee
*/
public interface Hashable {
    /**
     * Converts an instance of a hashable data type to a non-negative integer.
     * @return the integer value.
     */
    int key();
}

 