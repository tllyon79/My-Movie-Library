package mml.Model;

import java.util.*;

public class MovieRating {
    private double MovieRatings;
    private LinkedList<String> ReviewList;

    public double getRating() {
        return MovieRatings;
    }

    public LinkedList<String> getReviews() {
        return ReviewList;
    }

// rate movie method

    void RateMovie() {
        double RateCount;       //counts total number of user ratings to calculate global rating
        double PreRating;    //stores total of all ratings before averaging

//scan in user rating 1-5

        System.out.println("Enter a rating from 1 to 5");

        Scanner rater = new Scanner(System.in);
        double userRate = rater.nextDouble();

        if (userRate < 1 || userRate > 5) {
            System.out.printf("Invalid entry, please enter a rating from 1 to 5");
        } else {
            System.out.printf("Thanks for rating!");

            //RateCount++; //increments as ratings are added.
            //PreRating = PreRating + userRate;  //PreRating stores total of all ratings , and will be averaged with RateCount
            //MovieRatings = PreRating / RateCount; //Stores average overall rating for a movie
        }
    }

    public void ReviewMovie() {

        LinkedList<String> ReviewList = new LinkedList<>();

        System.out.println("Please enter a review.");
//scan in user review and add to review list

        Scanner reviewer = new Scanner(System.in);
        reviewer.nextLine();  //might need this to escape 'nextDouble' above
        String userReview = reviewer.nextLine();

        ReviewList.add(userReview);  //adds user review to the user review list

    }
}
