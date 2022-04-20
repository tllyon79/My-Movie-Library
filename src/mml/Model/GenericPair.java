package mml.Model;

import java.util.Comparator;
import java.util.Objects;

/**
 * Generic class designed to store values of two different types
 *
 * @param <T1> The first type to store
 * @param <T2> The second type to store
 */
public class GenericPair<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> implements Pair{
    private T1 first;
    private T2 second;
    //even though this isn't a key-value pair, it's easier to give them distinct names

    /**
     * Public constructor for the generic pair
     *
     * @param t1 Object of type T1 to store in this pair
     * @param t2 Object of type T2 to store in this pair
     */
    public GenericPair(T1 t1, T2 t2) {
        first = t1;
        second = t2;
    }

    /**
     * Retrieves the value of the first type of the generic pair
     *
     * @return The value of the first type
     */
    public T1 getKey() {
        return first;
    }

    /**
     * Retrieves the value of the second type of the generic pair
     *
     * @return The value of the second type
     */
    public T2 getValue() {
        return second;
    }

    /**
     * Compares the key of this pair to another pair
     * @param other The pair to compare to
     * @return -1 if this key is less than the other; 1 if this key is greater than the other; 0 if the two are equal
     */
    @Override
    public int compareKey(Pair other) {
        return other.getKey().compareTo(getKey());
    }

    /**
     * Compares the value of this pair to another pair
     * @param other The pair to compare to
     * @return -1 if this value is less than the other; 1 if this value is greater than the other; 0 if the two are equal
     */
    @Override
    public int compareValue(Pair other) {
        return other.getValue().compareTo(getValue());
    }

    /**
     * Sets the value of the first type of the generic pair
     *
     * @param first The value of type T1 to set
     */
    public void setKey(T1 first) {
        this.first = first;
    }

    /**
     * Sets the value of the second type of the generic pair
     *
     * @param second The value of type T2 to set
     */
    public void setValue(T2 second) {
        this.second = second;
    }

    //these two were auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericPair<?, ?> pair = (GenericPair<?, ?>) o;
        return first.equals(pair.first) && second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
