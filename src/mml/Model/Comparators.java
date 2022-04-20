package mml.Model;

import java.util.Collections;
import java.util.List;
import java.util.Comparator;

import static java.lang.Math.signum;

/**
 * Compares Movies by their Genre
 */
class SortByGenre implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        List<String> g1 = o1.getGenre();
        List<String> g2 = o2.getGenre();
        Collections.sort(g1);
        Collections.sort(g2);
        //now do comparisons on the first nonequal genre
        for (int i = 0; i < Math.min(g1.size(),g2.size()); i++) {
            if(g1.get(i).compareTo(g2.get(i)) != 0){
                return g1.get(i).compareTo(g2.get(i));
            }
        }
        return 0;
    }
}

/**
 * Compares Movies by their Title
 */
class SortByTitle implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}

/**
 * Compares Movies by their Title, in the reverse order
 */
class SortByTitleInverse implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return -(o1.getTitle().compareTo(o2.getTitle()));
    }
}
//user rating? website rating?

/**
 * Compares Movies by the IMDB rating in the data provided
 */
class SortByRating implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        Double r1 = Double.parseDouble(o1.getImdbRating());
        Double r2 = Double.parseDouble(o2.getImdbRating());
        double retVal = ( r1 - r2 );
        int checkVal = (int) signum(retVal);
        return checkVal;
    }
}
/**
 * Compares Movies by the IMDB rating in the data provided, in the reverse order
 */
class SortByRatingInverse implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        Double r1 = Double.parseDouble(o1.getImdbRating());
        Double r2 = Double.parseDouble(o2.getImdbRating());
        double retVal = ( r1 - r2 );
        int checkVal = (int) signum(retVal);
        return -checkVal;
    }
}

/**
 * Compares Movies by their release year
 */
class SortByYear implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getYear().compareTo(o2.getYear());
    }
}

/**
 * Compares Pairs by their keys
 */
class SortPairsByKey implements Comparator<Pair>{

    @Override
    public int compare(Pair o1, Pair o2) {
        return o1.compareKey(o2);
    }
}

/**
 * Compares Pairs by their values
 */
class SortPairsByValue implements Comparator<Pair>{

    @Override
    public int compare(Pair o1, Pair o2) {
        return o1.compareValue(o2);
    }
}
