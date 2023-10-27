/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maps;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mayte
 */
public class HashTableMapLPTest {
    
    
    private HashTableMapLP<String, String> instance;
    
    public HashTableMapLPTest() {
    }

    /**
     * Test of size method, of class HashTableMapLP.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        instance = new HashTableMapLP<>();
        assertEquals(instance.size(),0);
        
        instance.put("912127659A", "Jose");
        instance.put("912127658B", "Angel");
        instance.put("912127657C", "Abraham");
        instance.put("912127656D", "Mayte");
        instance.put("912127655E", "Raul");
        assertEquals(instance.size(),5);
        
        instance.remove("912127658B");
        assertEquals(instance.size(),4);
    }

    /**
     * Test of isEmpty method, of class HashTableMapLP.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        instance = new HashTableMapLP<>();
        assertTrue(instance.isEmpty());
        instance.put("912127659A", "Pedro");
        assertFalse(instance.isEmpty());
        instance.remove("912127659A");
        assertTrue(instance.isEmpty());
    }

    /**
     * Test of get method, of class HashTableMapLP.
     */
    @Test
    public void testPutAndGet() {
        instance = new HashTableMapLP<>();

        try {
            instance.get(null);
            fail("Deberia lanzar: InvalidKeyException");

        } catch (RuntimeException e) {
            // OK
        }

        try {
            instance.put(null, "Jose");
            fail("Deberia lanzar: InvalidKeyException");

        } catch (RuntimeException e) {
            // OK
        }

        assertEquals(instance.get("Jose"), null);

        instance.put("912127659A", "Pedro");
        instance.put("912127656D", "Maria");
        instance.put("912127624G", "Andres");
        assertEquals(instance.size(), 3);
        assertEquals(instance.get("912127659A"), "Pedro");
        assertEquals(instance.get("912127624G"), "Andres");
        instance.put("912127659A", "Mayte");
        assertEquals(instance.get("912127659A"), "Mayte");
    }

    

    /**
     * Test of remove method, of class HashTableMapLP.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        instance = new HashTableMapLP<>();
        instance.put("912127659A", "Pedro");
        instance.put("912127624G", "Andres");
        instance.remove("912127624G");
        assertEquals(instance.size(), 1);
        assertEquals(instance.get("912127659A"), "Pedro");
        assertEquals(instance.get("912127624G"), null);

        try {
            instance.remove(null);
            fail("Deberia lanzar: InvalidKeyException");

        } catch (RuntimeException e) {
            // OK
        }
    }

    

    /**
     * Test of keys method, of class HashTableMapLP.
     */
    @Test
    public void testKeys() {
        System.out.println("keys");
        instance = new HashTableMapLP<>();
        instance.put("912127658B", "Angel");
        instance.put("912127659A", "Jose");
        instance.put("912127624G", "Andres");
        Iterable<String> keys = instance.keys();

        ArrayList<String> l = new ArrayList<>();

        for (String k : keys) {
            l.add(k);
        }

        assertEquals(l.size(), 3);

        Iterator<Entry<String, String>> it = instance.iterator();

        while (it.hasNext()) {
            String s = it.next().getKey();
            assertEquals(l.contains(s), true);
        }
    }

    /**
     * Test of values method, of class HashTableMapLP.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        instance = new HashTableMapLP<>();
        instance.put("912127658B", "Angel");
        instance.put("912127659A", "Jose");
        instance.put("912127624G", "Andres");
        Iterable<String> values = instance.values();
        Iterator<String> it = values.iterator();

        ArrayList<String> l = new ArrayList<>();

        while (it.hasNext()) {
            l.add(it.next());
        }

        assertEquals(l.size(), 3);
        assertEquals(l.contains(("Angel")), true);
        assertEquals(l.contains(("Jose")), true);
        assertEquals(l.contains(("Andres")), true);
    }

    /**
     * Test of entries method, of class HashTableMapLP.
     */
    @Test
    public void testEntries() {
        System.out.println("entries");
        instance = new HashTableMapLP<>();
        instance.put("912127658B", "Angel");
        instance.put("912127659A", "Jose");
        instance.put("912127624G", "Andres");
        Iterable<Entry<String, String>> entries = instance.entries();
        ArrayList<Entry<String, String>> l = new ArrayList<>();

        for (Entry<String, String> i : entries) {
            l.add(i);
        }

        assertEquals(l.size(), 3);

        Iterator<Entry<String, String>> it = instance.iterator();

        while (it.hasNext()) {
            Entry<String, String> e = it.next();
            assertEquals(l.contains(e), true);
        }
    }

    

    /**
     * Test of rehash method, of class HashTableMapLP.
     */
    @Test
    public void testRehash() {
        System.out.println("entries");
        instance = new HashTableMapLP<>();
        instance.put("912127658B", "Angel");
        instance.put("912127659A", "Jose");
        instance.put("912127624G", "Andres");

        instance.rehash(100);

        System.out.println("rehash");

        HashTableMapLP<Integer,Integer> listin1 = new HashTableMapLP<>(10);
        final int NUM_ENTRIES = 1000;

        // Testing size
        for (int cont = 0; cont < NUM_ENTRIES; cont++) {
            listin1.put(cont, cont);
        }
        assertEquals(listin1.size(), NUM_ENTRIES);
        
    }

    @Test
    public void testRehas2(){

    }
    
}
