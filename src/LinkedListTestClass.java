import org.junit.Test;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class LinkedListTestClass {

    @Test
    public void append_test_01(){

        LinkedList<String, Integer> list = new LinkedList<>();
        list.append("Emanuel", 27);
        list.append("Samilson", 23);

        String result = list.toString();
        assertEquals("{Emanuel=27, Samilson=23}", result);
        System.out.println("append(key, value), got " +  result);
    }


    @Test
    public void prepend_test_01(){

        LinkedList<String, Integer> list = new LinkedList<>();
        list.prepend("Peter", 20);
        list.prepend("Ney", 26);

        String result = list.toString();
        assertEquals("{Ney=26, Peter=20}", result);
        System.out.println("prepend(key, value), got " +  result);
    }

    @Test
    public void remove_test_01(){

        LinkedList<String, Integer> list = new LinkedList<>();
        list.append("Peter", 20);
        list.prepend("Ney", 26);
        list.prepend("Garret", 18);

        Integer value = list.remove("Ney");

        String result = list.toString();
        System.out.println("remove(key, value), got " +  value);
        assertEquals("{Garret=18, Peter=20}", result);
    }

    @Test
    public void remove_test_02(){

        LinkedList<String, Integer> list = new LinkedList<>();
        list.append("Peter", 20);
        list.prepend("Ney", 26);
        list.prepend("Garret", 18);

        Integer value = list.remove("Garret");

        String result = list.toString();
        System.out.println("remove(key, value), got " +  value);
        assertEquals("{Ney=26, Peter=20}", result);
    }

    @Test
    public void remove_test_03(){

        LinkedList<String, Integer> list = new LinkedList<>();
        list.append("Peter", 20);
        list.prepend("Ney", 26);
        list.prepend("Garret", 18);

        Integer value = list.remove("Peter");

        String result = list.toString();
        System.out.println("remove(key, value), got " +  value);
        assertEquals("{Garret=18, Ney=26}", result);
    }

    @Test
    public void containsKey_test_01(){

        LinkedList<String, Integer> list = new LinkedList<>();
        list.append("Peter", 20);
        list.prepend("Ney", 26);
        list.prepend("Garret", 18);

        assertTrue(list.containsKey("Ney"));
        System.out.println("containsKey('Ney'), got " + true);
    }

    @Test
    public void containsKey_test_02(){

        LinkedList<String, Integer> list = new LinkedList<>();
        list.append("Peter", 20);
        list.prepend("Ney", 26);
        list.prepend("Garret", 18);

        assertFalse(list.containsKey("Emanuel"));
        System.out.println("containsKey('Emanuel'), got " + false);
    }

    @Test
    public void containsValue_test_01(){

        LinkedList<String, Integer> list = new LinkedList<>();
        list.append("Peter", 20);
        list.prepend("Ney", 26);
        list.prepend("Garret", 18);

        assertTrue(list.containsValue(26));
        System.out.println("containsValue(26), got " + true);
    }

    @Test
    public void containsValue_test_02(){

        LinkedList<String, Integer> list = new LinkedList<>();
        list.append("Peter", 20);
        list.prepend("Ney", 26);
        list.prepend("Garret", 18);

        assertFalse(list.containsValue(100));
        System.out.println("containsValue(100), got " + false);
    }

    @Test
    public void get_test_01(){
        LinkedList<String, Integer> list = new LinkedList<>();
        list.append("Peter", 20);
        list.prepend("Ney", 27);
        list.prepend("Garret", 18);

        int value = list.get("Ney");
        assertEquals(27, value);
        System.out.println("get('Ney'), got " + value);
    }

    @Test
    public void keySet_test_01(){
        LinkedList<String, Integer> list = new LinkedList<>();
        list.append("Peter", 20);
        list.prepend("Ney", 27);
        list.prepend("Garret", 18);

        Set<String> keySet = list.keySet();
        assertEquals("[Garret, Ney, Peter]", keySet.toString());
        System.out.println("keySet(), got " + keySet);
    }
}
