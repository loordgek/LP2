package com.sots.api.util.data;

public class Tuple<K, V> {

    private K key;

    private V val;

    public Tuple(K Key, V Val) {
        key = Key;
        val = Val;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Tuple<?, ?>)) {
            return false;
        }
        Tuple<?, ?> other = (Tuple<?, ?>) o;

        if (!(key.equals(other.getKey()))) {
            return false;
        }

        return val.equals(other.getVal());
    }

    public int hashCode() {
        return val.hashCode() + key.hashCode() * 17;
    }


}
