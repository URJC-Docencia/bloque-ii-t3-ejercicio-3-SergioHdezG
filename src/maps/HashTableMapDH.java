package maps;

/**
 * @param <K> The hey
 * @param <V> The stored value
 */
public class HashTableMapDH<K, V> extends AbstractHashTableMap<K, V> {

    private int q = 101;

    public HashTableMapDH(int size) {
        super(size);
    }

    public HashTableMapDH() {
        super();
    }

    public HashTableMapDH(int p, int cap) {
        super(p, cap);
    }

    private int secondHash(int k){
        return this.q - (k % q);
    }
    @Override
    protected int offset(int hashKey, int p) {
        return p * secondHash(hashKey);
    }
}
