package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args){

                MovieDAO movieDAO = new MovieDAO();

                movieDAO.createMovie(new Movie("Inception", "Sci-Fi", 2010, 8.8));
                movieDAO.createMovie(new Movie("Interstellar", "Sci-Fi", 2014, 8.6));
                movieDAO.createMovie(new Movie("The Godfather", "Crime", 1972, 9.2));

                System.out.println("\nAll movies:");
                List<Movie> movies = movieDAO.readAllMovies();

                for (Movie movie : movies) {
                    System.out.println(movie);
                }

                System.out.println("\nUpdating movie rating:");
                movieDAO.updateMovieRating(1, 9.0);

                System.out.println("\nMovies after update:");
                for (Movie movie : movieDAO.readAllMovies()) {
                    System.out.println(movie);
                }

                System.out.println("\nDeleting movie:");
                movieDAO.deleteMovie(2);

                System.out.println("\nMovies after delete:");
                for (Movie movie : movieDAO.readAllMovies()) {
                    System.out.println(movie);
                }

    }
}
