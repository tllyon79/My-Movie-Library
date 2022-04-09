import java.util.Scanner;

public class MovieRating {
    {
        private double MovieRatings;
        private String ReviewList;
    }

    public double getRating() {
        return MovieRatings;
    }

    public LinkedList<String> getReviews() {
        return ReviewList;
    }

// rate movie method


    double

    void RateMovie() {
        double RateCount;       //counts total number of user ratings to calculate global rating
        double PreRating;    //stores total of all ratings before averaging

//scan in user rating 1-5

        system.out.printf("Enter a rating from 1 to 5");

        Scanner rater = new Scanner(System.in);
        double userRate = input.nextDouble();

        if (userRate < 1 || userRate > 5) {
            system.out.printf("Invalid entry, please enter a rating from 1 to 5");
        } else {
            system.out.printf("Thanks for rating!");

            RateCount++; //increments as ratings are added.
            PreRating = PreRating + userRate;  //PreRating stores total of all ratings , and will be averaged with RateCount
            MovieRatings = PreRating / RateCount; //Stores average overall rating for a movie
        }
    }

    public void ReviewMovie() {

        LinkedList<String> ReviewList = new LinkedList<>();

        system.out.printf("Please enter a review.");
//scan in user review and add to review list

        Scanner reviewer = new Scanner(System.in);
        input.nextLine();  //might need this to escape 'nextDouble' above
        String userReview = input.nextLine();

        ReviewList.add(userReview);  //adds user review to the user review list

    }
}
