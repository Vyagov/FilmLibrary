package project.model.view;

import java.util.HashSet;
import java.util.Set;

public class MovieViewModel extends BaseViewModel {
    private String movieTitle;
    private String poster;
    private String director;
    private String producer;
    private String screenwriter;
    private Integer duration;
    private Set<ActorViewModel> actors = new HashSet<>();

    public MovieViewModel() {
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<ActorViewModel> getActors() {
        return actors;
    }

    public void setActors(Set<ActorViewModel> actors) {
        this.actors = actors;
    }
}
