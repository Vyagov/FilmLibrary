package project.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class MovieBindingModel extends BaseBindingModel {
    private String movieTitle;
    private MultipartFile image;
    private String director;
    private String producer;
    private String screenwriter;
    private Integer duration;
    private String actorNames;

    public MovieBindingModel() {
    }

    @NotBlank(message = "Not be empty! ")
    @Length(min = 2, max = 200, message = "The title length must be more than 2 characters! ")
    @Pattern(regexp = "^[a-zA-Z0-9-\\s+,.!'\"_\\p{IsCyrillic}]+$", message = "Is not a text!")
    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    @NotNull(message = "Select an image! ")
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @NotNull(message = "Not be empty! ")
    @Length(min = 3, max = 200, message = "Name length must be more than 3 characters! ")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\p{IsCyrillic}]+$", message = "The name of the Director must be only letters!")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @NotNull(message = "Not be empty! ")
    @Length(min = 3, max = 200, message = "Name length must be more than 3 characters! ")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\p{IsCyrillic}]+$", message = "The name of the Producer must be only letters!")
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @NotNull(message = "Not be empty! ")
    @Length(min = 3, max = 200, message = "Name length must be more than 3 characters! ")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\p{IsCyrillic}]+$", message = "The name of the Screenwriter must be only letters!")
    public String getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    @NotNull(message = "Not be empty! ")
    @Digits(integer = 5, fraction = 0, message = "Length of value is not in range! ")
    @Min(value = 1, message = "The duration is not correct! ")
    @Max(value = 45000, message = "The duration is too large!")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @NotBlank(message = "Select actors! ")
    @NotNull(message = "Select at least one actor!")
    public String getActorNames() {
        return actorNames;
    }

    public void setActorNames(String actorNames) {
        this.actorNames = actorNames;
    }
}
