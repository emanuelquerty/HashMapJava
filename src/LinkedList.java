import java.util.LinkedHashSet;
import java.util.Set;

/*
 * An object of this class describes a linked list
 */
public class LinkedList<K, V> {
    private HashNode<K, V> head;
    private HashNode<K, V> tail;
    private int size;

    public LinkedList(){
        this.head = null;
        this.size = 0;
    }

    /*
     * Given a key and value, create a node and add it to the head of the list
     *
     * @param key: the key to associate with the node
     * @param value: the value to associate with the node
     *
     * @return none
     */
    public void prepend(K key, V value){

        if (head == null){
            head = new HashNode<>(key, value);
            tail = head;
        }else{
            HashNode<K, V> node = new HashNode<>(key, value);
            node.setNext(head);
            head = node;
        }
        size++;

    }


    /*
     * Given a key and value, create a node and add it to the end of the list
     *
     * @param key: the key to associate with the node
     * @param value: the value to associate with the node
     *
     * @return none
     */
    public void append(K key, V value){
        if (head == null){
            head = new HashNode<>(key, value);
            tail = head;
        }else{
            HashNode<K, V> node = new HashNode<>(key, value);
            tail.setNext(node);
            tail = node;
        }
        size++;
    }


    /*
     * Removes the node associated with the given key
     *
     * @param key: the key associated to the node to be removed
     *
     * @return value/null: the value associated with the key if it exists. Null, otherwise
     */
    public V remove(K key){
        HashNode<K, V> temp = head;

        if (head != null && head.getKey().equals(key)){
            V value = head.getValue();
            head = head.getNext();
            size--;
            return value;
        }

        while (temp != null && temp.getNext() != null){
            if ( temp.getNext().getKey().equals(key)){
                V value = temp.getNext().getValue();
                temp.setNext( temp.getNext().getNext() );
                size--;
                return value;

            }

            temp = temp.getNext();
        }

        return null;
    }


    /*
     * Returns but do not remove the value associated with this key
     *
     * @param key: the key associated to the node to be removed
     *
     * @return value/null: the value associated with the key if it exists. Null, otherwise
     */
    public V get(K key) {

        HashNode<K, V> temp = head;
        while (temp!= null){
            if (temp.getKey().equals(key)){
                return temp.getValue();
            }

            temp = temp.getNext();
        }
        return null;
    }


    /*
     * Returns the size of the list
     *
     * @param void
     *
     * @return size: the size of this list
     */
    public int size(){
        return this.size;
    }


    /*
     * Returns all keys associated with the mappings in this list
     *
     * @param void
     *
     * @return keySet: the key set containing all keys
     */
    public Set<K> keySet(){

        Set<K> keySet = new LinkedHashSet<>();
        HashNode<K, V> temp = head;

        while (temp!= null){
           keySet.add(temp.getKey());
            temp = temp.getNext();
        }

        return keySet;
    }


    /*
     * Returns true if the list contains given key
     *
     * @param key: the key to check against the list
     *
     * @return true/false: true of the list contains the given key. False, otherwise.
     */
    public boolean containsKey(K key){
        HashNode<K, V> temp = head;
        while (temp != null){

            if (temp.getKey().equals(key)){
                return true;
            }

            temp = temp.getNext();
        }

        return false;
    }

    /*
     * Returns true if the list contains given value
     *
     * @param value: the value to check against the list
     *
     * @return true/false: true of the list contains the given value. False, otherwise.
     */
    public boolean containsValue(V value){
        HashNode<K, V> temp = head;
        while (temp != null){

            if (temp.getValue().equals(value)){
                return true;
            }

            temp = temp.getNext();
        }

        return false;
    }


    /*
     * Returns the string representation of this list
     *
     * @param void
     *
     * @return string: the string representation of this list
     */
    public String toString(){
        HashNode<K, V>  temp = head;

        StringBuilder builder = new StringBuilder();
        builder.append("{");
        while (temp != null){
            builder.append(temp.getKey());
            builder.append("=");
            builder.append(temp.getValue());
            builder.append(", ");

            temp = temp.getNext();
        }
        return builder.substring(0, builder.length() - 2) + "}";
    }


}
