package mml.Model;

import java.util.Comparator;

class SortByGenre implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getGenre().compareTo(o2.getGenre());
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
        double num1 = 0;
        double den1 = 0;
        double num2 = 0;
        double den2 = 0;
        for (WebsiteRating website : o1.getWebsiteRatings()) {
            num1 += website.GetValueAsDouble();
            den1++;
        }
        for (WebsiteRating website : o2.getWebsiteRatings()) {
            num2 += website.GetValueAsDouble();
            den2++;
        }
        if(den1 == 0) return -1;
        if(den2 == 0) return 1;
        return (int)((num1/den1) - (num2/den2));
    }
}
class SortByRatingInverse implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        double num1 = 0;
        double den1 = 0;
        double num2 = 0;
        double den2 = 0;
        for (WebsiteRating website : o1.getWebsiteRatings()) {
            num1 += website.GetValueAsDouble();
            den1++;
        }
        for (WebsiteRating website : o2.getWebsiteRatings()) {
            num2 += website.GetValueAsDouble();
            den2++;
        }
        if(den1 == 0) return -1;
        if(den2 == 0) return 1;
        return -(int)((num1/den1) - (num2/den2));
    }
}

class SortByYear implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getYear().compareTo(o2.getYear());
    }
}
