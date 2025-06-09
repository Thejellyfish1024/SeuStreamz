package bd.edu.seu.seustreamz.services;

import bd.edu.seu.seustreamz.interfaces.MovieInterface;
import bd.edu.seu.seustreamz.models.Movie;
import bd.edu.seu.seustreamz.models.User;
import bd.edu.seu.seustreamz.utils.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieService implements MovieInterface {
    @Override
    public void insertMovie(Movie movie) {
        int id = movie.getId();
        String title = movie.getTitle();
        String description = movie.getDescription();
        String genre = movie.getGenre();
        String videoLink = movie.getVideoLink();
        String thumbnailLink = movie.getThumbnailLink();
        List<String> viewers = movie.getViewers();
        List<String> likedBy = movie.getLikedBy();
        LocalDateTime createdAt = movie.getCreatedAt();

        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "INSERT INTO movies (id, title, description, genre, videoLink, thumbnailLink, viewers, likedBy, createdAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pt = connection.prepareStatement(query);

            pt.setInt(1, id);
            pt.setString(2, title);
            pt.setString(3, description);
            pt.setString(4, genre);
            pt.setString(5, videoLink);
            pt.setString(6, thumbnailLink);
            pt.setString(7, String.join(",", viewers));
            pt.setString(8, String.join(",", likedBy));
            pt.setString(9, createdAt.toString());
            pt.executeUpdate();
            System.out.println("Inserted movie into database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie getMovieById(int id) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM movies WHERE id = ?";
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                String genre = rs.getString("genre");
                String videoLink = rs.getString("videoLink");
                String thumbnailLink = rs.getString("thumbnailLink");
                String viewers = rs.getString("viewers");
                String likedBy = rs.getString("likedBy");
                LocalDateTime createdAt = LocalDateTime.parse(rs.getString("createdAt"));

                List<String> viewersList = List.of(viewers.split(","));
                List<String> likedByList = List.of(likedBy.split(","));

                return new Movie(id, title, description, genre, videoLink, thumbnailLink, viewersList, likedByList, createdAt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM movies";
            PreparedStatement pt = connection.prepareStatement(query);
            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String genre = rs.getString("genre");
                String videoLink = rs.getString("videoLink");
                String thumbnailLink = rs.getString("thumbnailLink");
                String viewers = rs.getString("viewers");
                String likedBy = rs.getString("likedBy");
                LocalDateTime createdAt = LocalDateTime.parse(rs.getString("createdAt"));

                List<String> viewersList = viewers.isEmpty()
                        ? new ArrayList<>()
                        : Arrays.stream(viewers.split(","))
                        .filter(s -> !s.isBlank())
                        .toList();

                List<String> likedByList = likedBy.isEmpty()
                        ? new ArrayList<>()
                        : Arrays.stream(likedBy.split(","))
                        .filter(s -> !s.isBlank())
                        .toList();

                movies.add(new Movie(id, title, description, genre, videoLink, thumbnailLink, viewersList, likedByList, createdAt));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }

    @Override
    public Movie getMovieByTitle(String title) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM movies WHERE title = ?";
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setString(1, title);
            ResultSet rs = pt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                String genre = rs.getString("genre");
                String videoLink = rs.getString("videoLink");
                String thumbnailLink = rs.getString("thumbnailLink");
                String viewers = rs.getString("viewers");
                String likedBy = rs.getString("likedBy");
                LocalDateTime createdAt = LocalDateTime.parse(rs.getString("createdAt"));

                List<String> viewersList = List.of(viewers.split(","));
                List<String> likedByList = List.of(likedBy.split(","));

                return new Movie(id, title, description, genre, videoLink, thumbnailLink, viewersList, likedByList, createdAt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void updateMovie(int id, Movie movie) {
        String title = movie.getTitle();
        String description = movie.getDescription();
        String genre = movie.getGenre();
        String videoLink = movie.getVideoLink();
        String thumbnailLink = movie.getThumbnailLink();

        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "UPDATE movies SET title = ?, description = ?, genre = ?, videoLink = ?, thumbnailLink = ? WHERE id = ?";
            PreparedStatement pt = connection.prepareStatement(query);

            pt.setString(1, title);
            pt.setString(2, description);
            pt.setString(3, genre);
            pt.setString(4, videoLink);
            pt.setString(5, thumbnailLink);
            pt.setInt(6, id);

            int updatedRows = pt.executeUpdate();

            if (updatedRows > 0) {
                System.out.println("Movie updated successfully");
            } else {
                System.out.println("No movie found with ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update movie", e);
        }
    }

    @Override
    public void deleteMovie(int id) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "DELETE FROM movies WHERE id = ?";
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setInt(1, id);

            int deletedRows = pt.executeUpdate();

            if (deletedRows > 0) {
                System.out.println("Movie deleted successfully with ID: " + id);
            } else {
                System.out.println("No movie found with ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete movie with ID: " + id, e);
        }
    }


}
