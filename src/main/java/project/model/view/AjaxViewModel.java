package project.model.view;

import java.util.ArrayList;
import java.util.List;

public class AjaxViewModel {
    private String message;
    private List<MovieViewModel> movies = new ArrayList<>();

    public AjaxViewModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MovieViewModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieViewModel> movies) {
        this.movies = movies;
    }
}
