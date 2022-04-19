package mml.Model;

import java.util.Collections;
import java.util.List;
import java.util.Comparator;

import static java.lang.Math.signum;

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
class SortByTitle implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
class SortByTitleInverse implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return -(o1.getTitle().compareTo(o2.getTitle()));
    }
}
//user rating? website rating?
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

class SortByYear implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getYear().compareTo(o2.getYear());
    }
}
