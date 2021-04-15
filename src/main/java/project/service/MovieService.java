package project.service;

import project.model.service.MovieServiceModel;
import project.model.view.MovieViewModel;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    void addMovie(MovieServiceModel movieServiceModel) throws IOException;

    List<MovieServiceModel> findAllMovies();

    List<MovieViewModel> getAllMovies();

    MovieViewModel findById(String id);

    void delete(String id);
}
