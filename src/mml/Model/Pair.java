package mml.Model;

import java.util.Objects;

/**
 * An interface that describes a object that holds two other objects of potentially differing types
 * @param <T1> The type of the first object
 * @param <T2> The type of the second object
 */
public interface Pair<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> {
    /**
     * Accessor for the key of the pair
     * @return The key, of type T1
     */
    public T1 getKey();

    /**
     * Accessor for the value of the pair
     * @return The value, of type T2
     */
    public T2 getValue();

    /**
     * Compares the keys of two pairs
     * @param other The other pair to compare to
     * @return -1 if this pair's key is less than the other; 1 if this pair's key is greater than the other; 0 if they are equal
     */
    public int compareKey(Pair other);

    /**
     * Compares the values of two pairs
     * @param other The other pair to compare to
     * @return -1 if this pair's value is less than the other; 1 if this pair's value is greater than the other; 0 if they are equal
     */
    public int compareValue(Pair other);
}

