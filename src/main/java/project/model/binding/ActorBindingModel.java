package project.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ActorBindingModel extends BaseBindingModel {
    private MultipartFile image;
    private String name;
    private String born;
    private String biography;

    public ActorBindingModel() {
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
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\p{IsCyrillic}]+$", message = "The name of the Actor must be only letters! ")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Input place and date of birth! ")
    @Length(min = 10, max = 200, message = "Born length must be more than 10 characters! ")
    @Pattern(regexp = "^[a-zA-Z0-9-\\s+,.!\"_\\p{IsCyrillic}]+$", message = "Is not a text! ")
    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    @NotBlank(message = "Not be empty! ")
    @Length(min = 10, message = "Biography length must be more than 10 characters! ")
    @Pattern(regexp = "^[a-zA-Z0-9-\\s+,.!\"_\\p{IsCyrillic}]+$", message = "Is not a text! ")
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
