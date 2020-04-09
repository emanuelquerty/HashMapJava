import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class MyHashMapTestClass {

    @Test
    public void put_test_01(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
        map.put("forth", 4);

        assertEquals("{first=1, second=2, forth=4, third=3}", map.toString());
        System.out.println("toString(), got " + map.toString());
    }


    @Test
    public void put_test_02(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
        map.put("forth", 4);
        map.put("first", 200);

        assertEquals("{first=200, second=2, forth=4, third=3}", map.toString());
        System.out.println("toString(), got " + map.toString());
    }

    @Test
    public void put_test_03(){
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("first", "hey");
        map.put("second", "man");
        map.put("third", "how");
        map.put("forth", "are");
        map.put("first", "you");

        assertEquals("{first=you, second=man, forth=are, third=how}", map.toString());
        System.out.println("toString(), got " + map.toString());
    }


    @Test
    public void get_test_01(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("first", 2);
        map.put("second", 4);

        int result = map.get("second");
        assertEquals(4, result);
        System.out.println("map.get('second'), got " + result);
    }

    @Test
    public void get_test_02(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("first", 2);
        map.put("second", 4);

        int result = map.get("first");
        assertEquals(2, result);
        System.out.println("map.get('first'), got " + result);
    }

    @Test
    public void containsKey_test01(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Emanuel", 27);
        map.put("Vanessa", 25);

        boolean result = map.containsKey("Vanessa");
        assertTrue(result);
        System.out.println("containsKey('Vanessa'), got " + true);
    }

    @Test
    public void containsValue_test01(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Emanuel", 27);
        map.put("Vanessa", 25);

        boolean result = map.containsValue(27);
        assertTrue(result);
        System.out.println("containsValue(27), got " + true);
    }

    @Test
    public void clear_test01(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Emanuel", 27);
        map.put("Vanessa", 25);

        map.clear();
        assertEquals("{}", map.toString());
        System.out.println("clear(), got " + map);
    }

    @Test
    public void isEmpty_test01(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Emanuel", 27);
        map.put("Vanessa", 25);

        map.clear();
        assertTrue(map.isEmpty());
        System.out.println("isEmpty(), got " + true);
    }

    @Test
    public void size_test01(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Emanuel", 27);
        map.put("Vanessa", 25);

       int result = map.size();
       assertEquals(2, result);
        System.out.println("size(), got " + result);
    }

    @Test
    public void keySet_test01(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Emanuel", 27);
        map.put("Vanessa", 25);

        Set<String> result = map.keySet();
        List<String> sorted = new ArrayList<>(result);
        Collections.sort(sorted);

        assertEquals("[Emanuel, Vanessa]", sorted.toString());
        System.out.println("keySet(), got " + sorted);
    }

    @Test
    public void remove_test01(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Emanuel", 27);
        map.put("Vanessa", 25);

        int result = map.remove("Vanessa");
        assertEquals(25, result);
        System.out.println("remove(Vanessa), got " + result);
    }

    @Test
    public void printTable_test_01(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("ExampleKeyX", 22);
        map.put("ExampleKeyY", 33);

        map.printTable();
    }

    @Test
    public void printTable_test_02(){
        MyHashMap<String, Integer> map = new MyHashMap<>();
        List<String> names = new ArrayList<>(Arrays.asList("Emanuel", "Mateus", "Inacio", "Helder", "Francisco",
                "Iracelma", "Gizela", "Caxito", "Amelia", "Andre", "Maria", "Candia")) ;

        for (String name : names)  map.put(name, 22);

        map.printTable();
    }







}
