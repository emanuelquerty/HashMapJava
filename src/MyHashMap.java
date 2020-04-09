import java.util.*;
import java.util.LinkedList;

/*
 * An object of this class describes a hash map
 */
public class MyHashMap<K, V> {
    private int numBuckets;
    private ArrayList< LinkedList > map;
    private int size;


    public MyHashMap(){
        map = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            LinkedList bucket = new LinkedList();
            map.add(bucket);
        }
        size = 0;
        numBuckets = 8;
    }


    /*
     * Removes all of the mappings from this map. The map will be empty after this call returns.
     *
     * @param void
     *
     * @return none
     */
    public void clear(){
        map = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            LinkedList bucket = new LinkedList();
            map.add(bucket);
        }
        size = 0;
    }


    /*
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key: the key to check against the hash map
     *
     * @return true/false: true if a mapping for the specified key exists. False, otherwise
     */
    public boolean containsKey(K key){

        int hashCode = hash(key);
        LinkedList bucket = map.get(hashCode);

        if (bucket.containsKey(key)) return true;

        return false;
    }


    /*
     * Returns true if this map maps one or more keys to the specified value.
     *
     * @param value: the value to check against the hash map
     *
     * @return true/false: true if one or more keys are mapped to the specified value.
     * False, otherwise.
     */
    public boolean containsValue(V value){

        for (LinkedList bucket : map){
            if (bucket.containsValue(value)) return true;
        }

        return false;
    }


    /*
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     *
     * @params key
     * @return value/null
     */
    public V get(K key){

        int hashCode = hash(key);
        LinkedList bucket = map.get(hashCode);

        if (bucket.containsKey(key)) return bucket.get(key);

        return null;
    }


    /*
     * Returns true if this map contains no key-value mappings.
     *
     * @param void
     *
     * @return true/false: true if this map contains no key-value mappings. False, otherwise.
     */
    public boolean isEmpty(){
        return this.size() == 0;
    }

    /*
     * Returns a Set view of the keys contained in this map.
     *
     * @param void
     *
     * @return hashSet: the set of all values
     */
    public Set<K> keySet(){

        Set<K> keySet = new HashSet<>();
        for ( LinkedList bucket : map){

            HashNode<K, V> node = bucket.head();
            while (node != null){
                keySet.add( node.getKey() );
                node = node.getNext();
            }

        }

        return Collections.unmodifiableSet( keySet );
    }


    /*
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key: the key to associate with the specified value
     * @param value: the value to associate with the specified key
     *
     * @return value/null: the previous value associated with key, or null if there was no mapping for key.
     * (A null return can also indicate that the map previously associated null with key.)
     */
    public V put(K key, V value){

        int hashCode = hash(key);
        LinkedList bucket = map.get(hashCode);

        // Update value if given key is in the bucket already
        if (bucket.containsKey(key)) {

            HashNode<K, V> node = bucket.node(key);
            V prevValue = node.getValue();
            node.setValue(value); // update the value

            // there a mapping for the key, so we return the previous value
            return prevValue;
        }

        // there was no mapping for the key so add the key-value mapping
        // and return null as previous value associated with the key
        bucket.prepend(key, value);
        return null;
    }


    /*
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key: the key associated to the mapping to remove
     *
     * @return value/null: the previous value associated with key, or null if there was no mapping for key.
     * (A null return can also indicate that the map previously associated null with key.)
     */
    public V remove(K key){

        int hashCode = hash(key);
        LinkedList bucket = map.get(hashCode);

        if (bucket.containsKey(key)) return bucket.remove(key);

        return null;
    }


    /*
     * Returns the number of key-value mappings in this map.
     *
     * @param void
     *
     * @return size: the number of key-value mappings, the size of, this hash map
     */
    public int size(){

        for (LinkedList bucket : map){
            size += bucket.size;
        }

        return size;
    }


    /*
     * Outputs how many conflicts occur at each bucket and list the keys in that bucket.
     * No matter what, the hashTable should always have 8 buckets!
     */
    public void printTable(){

        int index = 0;
        int totalConflicts = 0;
        for ( LinkedList bucket : map){

            // Print in the format: "Index 6: (0 conflicts), [ExampleKeyY, ]"
            int bucketConflicts = bucket.size() > 0 ? bucket.size() - 1 : bucket.size();
            StringBuilder builder = new StringBuilder();

            builder.append("Index ");
            builder.append(index);
            builder.append(": (");
            builder.append( bucketConflicts);
            builder.append(" conflicts), ");

            // Format the key set
            String str = bucket.keyListPrintTable().toString();
            str = str.length() > 2 ? str.substring(0, str.length() - 1) + ", ]" : str;
            builder.append(str);


            // update the index and total conflicts
            index++;
            totalConflicts += bucketConflicts;

            // Print the output
            System.out.println(builder);
        }
        System.out.println("Total # of conflicts: " + totalConflicts);
        System.out.println();
    }


    /*
     * Returns the string representation of this hash map is the format
     * "{key1=value1, key2=value2, ... keyn= valuen"
     *
     * @param void
     *
     * @return string: the string representation of this hash map
     */
    public String toString(){

        if (this.size() == 0) return "{}";

        StringBuilder builder = new StringBuilder();
        LinkedList bucket = map.get(0);

        String str = bucket.toString().substring( 0, bucket.toString().length() - 1 );
        builder.append(str);
        builder.append(", ");

        for (int i = 1; i < numBuckets; i++){
            bucket = map.get(i);

            if (bucket.size() == 0) continue;

            str = bucket.toString().substring( 1, bucket.toString().length() - 1 );
            builder.append(str);
            builder.append(", ");

        }


        return builder.toString().substring(0, builder.toString().length() - 2) + "}";
    }

    /*
     * Hashes a given key and returns the hash(index) associated to the given key
     *
     * @params key
     *
     * @return index: the hash code (index) associated to this key
     */
    private int hash(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return Math.abs(index);
    }



    /**************************************************************
     * An object of this class describes a linked list
     **********************************************************8***/
    /*
     * An object of this class describes a linked list
     */
    public class LinkedList {
        private HashNode<K, V> head;
        private HashNode<K, V> tail;
        private int size;

        public LinkedList(){
            this.head = null;
            this.size = 0;
        }


        /*
         * Returns the head of the list
         */
        public HashNode<K, V>  head(){
            return this.head;
        }

        /*
         * Returns the tail of the list
         */
        public HashNode<K, V>  tail(){
            return this.tail;
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
         * Returns but do not remove the node associated with this key
         *
         * @param key: the key associated to the node to be returned
         *
         * @return node/null: the node associated with the key if it exists. Null, otherwise
         */
        public HashNode<K, V> node(K key) {

            HashNode<K, V> temp = head;
            while (temp!= null){
                if (temp.getKey().equals(key)){
                    return temp;
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
         * @return list: the list containing all keys
         */
        public List<K> keyListPrintTable(){

            List<K> list = new java.util.LinkedList<>();
            HashNode<K, V> temp = head;

            while (temp!= null){
                list.add(temp.getKey());
                temp = temp.getNext();
            }

            return list;
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

            if (size == 0) return "{}";

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

}
