# HashMap implementation in java 
A hash map implementation in java with generics and chaining as collision resolution.
This implementation always creates 8 buckets, so you can change in the HashMap constructor to 
any number of buckets. I used 8 buckets just for the purpose of collision resolution and analysis.

This hash map is backed by an ArrayList of linkedList for each bucket. The hash map is generic and it
contains some of the same methods as the java builtin hashmap such as add(), put(), containsKey(),
containsValue() ... etc. In addition, the hash map contains a method printTable() that prints the number of key collisions that
happens in a bucket after a certain number of key-value mappings are inserted in the hash map. The output of the printTable
method is as following:

Index 0: (0 conflicts), []

Index 1: (0 conflicts), []

Index 2: (0 conflicts), []

Index 3: (0 conflicts), []

Index 4: (0 conflicts), []

Index 5: (0 conflicts), [ExampleKeyX, ]

Index 6: (0 conflicts), [ExampleKeyY, ]

Index 7: (0 conflicts), []

Total # of conflicts: 0

Note: Notice that the hash map always has 8 buckets (again, you can change it this as you want).

# Usage:
There are more than two files in the src directory. However, you only need two files to use this hashmap.
Those two files contain all the implementation and they are: MyHashMap.java and HashNode.java.
The remaining files are just testCase files in JUnit and a LinkedList file that contains the same
linkedList that the HashMap uses (You don't need this file as the LinkedList is already nested within the
HashMap class). So you only need MyHashMap.java and HashNode.java to use the HashMap.

# Hash function

For the hashing the key, the following hash method was used:

private int hash(K key) {
  int hashCode = key.hashCode();
  int index = hashCode % numBuckets;
  return Math.abs(index);
}

# Collisions

This hash map uses chaining to handle the collisions of elements. The hash map always
have 8 buckets! Collisions will happen. This is the purpose of of the hash map having 8 buckets
since it makes it easier to see collisions happening for the purpose of understanding collision
resolution.

When a collision happens during insertion of a key-value pair, it is handled as following:

- PUT the (key,value) pairs at the FRONT of the list in the bucket.
- If the same key is passed in with a different value, do the value update in place.

# Methods implemented

Since this was for hash map analysis purposes, I didn't implement all methods that the builtin java
hash map has implemented. I implemented 9 methods only, which are some of the most used methods. 
The methods implemented are:

- clear
- containsKey
- containsValue
- get
- isEmpty
- keySet
- put
- remove - note the Java HashMap class has two versions. You only need to implement
the one with the signature: remove(key).
- size
- printTable

Notice that the only method not taken from the Java documentation is printTable. printTable() should
output how many conflicts occur at each bucket and list the keys in that bucket.

I hope this implementation helps you understand how a hashmap is implemented under the hood.
Feel free to copy the repository and use it as you would like, whether it is in a project
or for any other reason.

Happy coding,
Emanuel Inacio
