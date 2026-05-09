package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private static final String URL = "jdbc:h2:mem:moviesdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public MovieDAO() {
        createTable();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS movies (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    title VARCHAR(100) NOT NULL,
                    genre VARCHAR(50),
                    release_year INT,
                    rating DOUBLE
                )
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public void createMovie(Movie movie) {
        String sql = "INSERT INTO movies(title, genre, release_year, rating) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getGenre());
            statement.setInt(3, movie.getReleaseYear());
            statement.setDouble(4, movie.getRating());

            statement.executeUpdate();

            System.out.println("Movie added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding movie: " + e.getMessage());
        }
    }

    public List<Movie> readAllMovies() {
        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT * FROM movies";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Movie movie = new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getInt("release_year"),
                        resultSet.getDouble("rating")
                );

                movies.add(movie);
            }

        } catch (SQLException e) {
            System.out.println("Error reading movies: " + e.getMessage());
        }

        return movies;
    }

    public void updateMovieRating(int id, double newRating) {
        String sql = "UPDATE movies SET rating = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, newRating);
            statement.setInt(2, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Movie updated successfully.");
            } else {
                System.out.println("Movie not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating movie: " + e.getMessage());
        }
    }

    public void deleteMovie(int id) {
        String sql = "DELETE FROM movies WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Movie deleted successfully.");
            } else {
                System.out.println("Movie not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting movie: " + e.getMessage());
        }
    }
}