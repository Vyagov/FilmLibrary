package project.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actors")
public class Actor extends BaseEntity {
    private String pathToImage;
    private String name;
    private String born;
    private String biography;
    private Set<Movie> movies = new HashSet<>();

    public Actor() {
    }

    @Column(name = "path_to_image", nullable = false)
    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "born", nullable = false)
    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    @Column(name = "biography", columnDefinition = "TEXT", nullable = false)
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "actors_movies",
            joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

//    public void addMovie(Movie movie) {
//        this.movies.add(movie);
//        movie.getCast().add(this);
//    }
//
//    public void removeMovie(Movie movie) {
//        this.movies.remove(movie);
//        movie.getCast().remove(this);
//    }
}
