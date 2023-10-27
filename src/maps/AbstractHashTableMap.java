package maps;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Random;

/**
 * A hash table with linear probing and the MAD hash function
 */
/**
 * A hash table data structure that uses linear probing to handle collisions.
 * The hash function uses the built-in hashCode method and the
 * multiply-add-and-divide method. The load factor kept less than or equal to
 * 0.5. When the load factor reaches 0.5, the entries are rehashed into a new
 * bucket array with twice the capacity.
 *
 * @author R. Cabido, A. Duarte, and J. Velez
 * @param <K> Key type
 * @param <V> Value type
 */
abstract public class AbstractHashTableMap<K, V> implements Map<K, V> {

    /**
     * @param <T> Key type
     * @param <U> Value type
     *
     */
    private class HashEntry<T, U> implements Entry<T, U> {

        protected T key;
        protected U value;

        public HashEntry(T k, U v) {
            key = k;
            value = v;
        }

        @Override
        public U getValue() {
            return value;
        }

        @Override
        public T getKey() {
            return key;
        }

        public U setValue(U val) {
            U oldValue = value;
            value = val;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {

            if (o.getClass() != this.getClass()) {
                return false;
            }

            HashEntry<T, U> ent;
            try {
                ent = (HashEntry<T, U>) o;
            } catch (ClassCastException ex) {
                return false;
            }
            
            if ((ent.getKey() == null && this.key==null) && (ent.getValue() == null && this.value==null)){
                return true;
            }
            return ent.getKey().equals(this.key) && ent.getValue().equals(this.value);
        }

        /**
         * Entry visualization.
         */
        @Override
        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }

    private class HashTableMapIterator<T, U> implements Iterator<Entry<T, U>> {

        private int pos;
        private Entry<T, U> av;
        private HashEntry<T, U> [] b;
        public HashTableMapIterator(HashEntry<T, U>[] b, Entry<T, U> av, int numElems) {
            this.pos = 0;
            this.av = av;
            this.b = b;

            goToNextElement(this.pos);

        }

        private void goToNextElement(int start) {
            while (start <= this.b.length &&  (this.b[start] == null || this.b[start] == AVAILABLE)){
                start++;
            }
        }

        @Override
        public boolean hasNext() {
            return this.pos < this.b.length;
        }

        @Override
        public Entry<T, U> next() {
            Entry<T, U> auxEntry = this.b[this.pos];
            goToNextElement(this.pos++);
            return auxEntry;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapKeyIterator<T, U> implements Iterator<T> {
        HashTableMapIterator<T, U> it;
        public HashTableMapKeyIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public T next() {
            return this.it.next().getKey();
        }

        @Override
        public boolean hasNext() {
            return this.it.hasNext();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapValueIterator<T, U> implements Iterator<U> {
        HashTableMapIterator<T, U> it;
        public HashTableMapValueIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public U next() {
            return this.it.next().getValue();
        }

        @Override
        public boolean hasNext() {
            return this.it.hasNext();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }


    private int n;  // num elements
    private int cap; // capacity
    private int a, b, prime; // MAD function attributes
    private HashEntry<K, V> [] bucket;
    private HashEntry<K, V> AVAILABLE = new HashEntry<>(null, null);

    /**
     * Creates a hash table with prime factor 109345121 and capacity 1000.
     */
    protected AbstractHashTableMap() {
        this(109345121, 1000);
    }

    /**
     * Creates a hash table with prime factor 109345121 and given capacity.
     *
     * @param cap initial capacity
     */
    protected AbstractHashTableMap(int cap) {
        this(109345121, cap);
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     *
     * @param p prime number
     * @param cap initial capacity
     */
    protected AbstractHashTableMap(int p, int cap) {
        this.n = 0;
        this.cap = cap;
        this.prime = p;
        Random rand = new Random();
        this.a = rand.nextInt(prime-1)+1;
        this.b = rand.nextInt(prime);
        
        this.bucket = new HashEntry[this.cap];
    }

    /**
     * Returns the number of entries in the hash table.
     *
     * @return the size
     */
    @Override
    public int size() {
        return this.n;
    }

    /**
     * Returns whether or not the table is empty.
     *
     * @return true if the size is 0
     */
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return
     */
    protected int hashCode(K key) {
        int k =  key.hashCode();
        return ((a*k + b) % prime);
    }

    /**
     * Calculate the dispersed key.
     * Calculate the hash code and apply the compression function.
     *
     * @param key Key
     * @return
     */
    protected int hashValue(K key) {
        int x = hashCode(key);
        return Math.abs(x) % this.cap;
    }

    abstract protected int offset(int hashKey, int p);

    /**
     * Returns the value associated with a key.
     *
     * @param key
     * @return value
     */
    @Override
    public V get(K key) throws IllegalStateException {
        this.checkKey(key);
        
       int index = hashValue(key);
       final int endIndex = index;
       do {
           HashEntry<K, V> auxEntry = this.bucket[index];
           if (auxEntry == null) {
               return null;
           } else if (auxEntry.getKey().equals(key)) {
               return auxEntry.getValue();
           }
           index = (index + offset(hashCode(key), index)) % this.cap;
       }while(index != endIndex);
       return null;
    }

    /**
     * Put a key-value pair in the map, replacing previous one if it exists.
     *
     * @param key
     * @param value
     * @return value
     */
    @Override
    public V put(K key, V value) throws IllegalStateException {
        this.checkKey(key);
        
        int index = hashValue(key);
        final int endIndex = index;

        if (this.n == this.cap){
            rehash((int) ((int)this.cap * 1.5));
        }

        do{
            if (this.bucket[index] == null || this.bucket[index].equals(AVAILABLE)){
                this.bucket[index] = new HashEntry<K, V>(key, value);
                this.n++;
                return null;
            }
            else if(this.bucket[index].getKey().equals(key)){
                V oldValue = this.bucket[index].getValue();
                this.bucket[index].setValue(value);
                return oldValue;
            }
            else{
                index = (index + offset(hashCode(key), index)) % this.cap;
            }
        }while(index != endIndex);
        return null;
    }

    /**
     * Removes the key-value pair with a specified key.
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) throws IllegalStateException {
        this.checkKey(key);
        
        int index = hashValue(key);
        final int endIndex = index;

        do {
            if (this.bucket[index] == null){
                return null;
            }
            else if (this.bucket[index].getKey().equals(key)){
                V oldValue = this.bucket[index].getValue();
                this.bucket[index] = AVAILABLE;
                this.n--;
                return oldValue;
            }
            else if(this.bucket[index].equals(AVAILABLE) || !this.bucket[index].getKey().equals(key)){
                index = (index + offset(hashCode(key), index)) % this.cap;
            }
        }while (index != endIndex);
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableMapIterator<>(this.bucket, this.AVAILABLE, this.n);
    }

    /**
     * Returns an iterable object containing all of the keys.
     *
     * @return
     */
    @Override
    public Iterable<K> keys() {
        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                return new HashTableMapKeyIterator<>(new  HashTableMapIterator<>(bucket, AVAILABLE, n));
            }
        };
    }

    /**
     * Returns an iterable object containing all of the values.
     *
     * @return
     */
    @Override
    public Iterable<V> values() {
        return new Iterable<V>() {
            @Override
            public Iterator<V> iterator() {
                return new HashTableMapValueIterator<>(new  HashTableMapIterator<>(bucket, AVAILABLE, n));
            }
        };
    }

    /**
     * Returns an iterable object containing all of the entries.
     *
     * @return
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
        return new Iterable<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new HashTableMapIterator<>(bucket, AVAILABLE, n);
            }
        };
    }

    /**
     * Determines whether a key is valid.
     *
     * @param k Key
     */
    protected void checkKey(K k) {
        // We cannot check the second test (i.e., k instanceof K) since we do not know the class K
        if (k == null) {
            throw new IllegalStateException("Invalid key: null.");
        }
    }

    /**
     * Doubles the size of the hash table and rehashes all the entries.
     */
    protected void rehash() {
        rehash(this.cap * 2);
    }

    protected void rehash(int newcap) {
        HashEntry<K, V>[] oldBucket = this.bucket;
        this.cap = newcap;
        this.bucket = new HashEntry[newcap];
        Random rand = new Random();
        this.a = rand.nextInt(prime-1)+1;
        this.b = rand.nextInt(prime);

        for (HashEntry<K, V> auxEntry : oldBucket){
            if (auxEntry != null && !auxEntry.equals(AVAILABLE)){
                this.put(auxEntry.key, auxEntry.value);
            }
        }
    }

}
