package mml.Model;

public class Search {
    public static MovieList SearchList(MovieList m, String searchParameter)
    {
        MovieList ret = new MovieList();
        for(int i = 0; i < m.Movies.size(); i++){
            if(m.Movies[i].Title.contains(searchParameter)){
                ret.Movies.add(m.Movies[i]);
            }
        }
    }

    public static MovieList FilterList(MovieList m, FilterType t, String input){
        //for now
        return new MovieList();
    }

    public static void SortList(MovieList m, SortType s){
        //do nothing until implemented
    }
}
