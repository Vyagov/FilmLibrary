package project.model.view;

import java.util.HashSet;
import java.util.Set;

public class ActorViewModel extends BaseViewModel {
    private String image;
    private String name;
    private String born;
    private String biography;
    private Set<MovieViewModel> movies = new HashSet<>();

    public ActorViewModel() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Set<MovieViewModel> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieViewModel> movies) {
        this.movies = movies;
    }
}
