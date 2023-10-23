package maps;

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
            return (ent.getKey().equals(this.key))
                    && (ent.getValue().equals(this.value));
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

        public HashTableMapIterator(HashEntry<T, U>[] b, Entry<T, U> av, int numElems) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        private void goToNextElement(int start) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public Entry<T, U> next() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapKeyIterator<T, U> implements Iterator<T> {
        public HashTableMapKeyIterator(HashTableMapIterator<T, U> it) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapValueIterator<T, U> implements Iterator<U> {
        public HashTableMapValueIterator(HashTableMapIterator<T, U> it) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public U next() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    /**
     * Creates a hash table with prime factor 109345121 and capacity 1000.
     */
    protected AbstractHashTableMap() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Creates a hash table with prime factor 109345121 and given capacity.
     *
     * @param cap initial capacity
     */
    protected AbstractHashTableMap(int cap) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     *
     * @param p prime number
     * @param cap initial capacity
     */
    protected AbstractHashTableMap(int p, int cap) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the number of entries in the hash table.
     *
     * @return the size
     */
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns whether or not the table is empty.
     *
     * @return true if the size is 0
     */
    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return
     */
    protected int hashCode(K key) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Calculate the dispersed key.
     * Calculate the hash code and apply the compression function.
     *
     * @param key Key
     * @return
     */
    protected int hashValue(K key) {
        throw new UnsupportedOperationException("Not yet implemented");
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
       throw new UnsupportedOperationException("Not yet implemented");
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
       throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Removes the key-value pair with a specified key.
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) throws IllegalStateException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException("Not yet implemented");

    }

    /**
     * Returns an iterable object containing all of the keys.
     *
     * @return
     */
    @Override
    public Iterable<K> keys() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns an iterable object containing all of the values.
     *
     * @return
     */
    @Override
    public Iterable<V> values() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns an iterable object containing all of the entries.
     *
     * @return
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
        throw new UnsupportedOperationException("Not yet implemented");
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
        throw new UnsupportedOperationException("Not yet implemented");
    }

    protected void rehash(int newcap) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
