package bd.edu.seu.seustreamz.interfaces;

import bd.edu.seu.seustreamz.models.Movie;

import java.util.List;

public interface MovieInterface {
    void insertMovie(Movie movie);
    Movie getMovieById(int id);
    List<Movie> getAllMovies();
    Movie getMovieByTitle(String title);
    void updateMovie(int id, Movie movie);
    void deleteMovie(int id);
}
