package project.model.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

public class ActorServiceModel extends BaseServiceModel {
    private MultipartFile image;
    private String name;
    private String born;
    private String biography;
    private Set<MovieServiceModel> movies = new HashSet<>();

    public ActorServiceModel() {
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
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

    public Set<MovieServiceModel> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieServiceModel> movies) {
        this.movies = movies;
    }
}
