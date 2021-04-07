package project.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserProfileUpdateBindingModel extends BaseBindingModel {
    private String firstName;
    private String lastName;
    private String username;
    private String email;

    public UserProfileUpdateBindingModel() {
    }

    @Length(min = 2, max = 35, message = "Name length must be between 2 and 35 characters! ")
    @Pattern(regexp = "^[a-zA-Z\\p{IsCyrillic}]+$", message = "Name must be only letters!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min = 2, max = 35, message = "Name length must be between 2 and 35 characters! ")
    @Pattern(regexp = "^[a-zA-Z\\p{IsCyrillic}]+$", message = "Name must be only letters!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email(message = "Enter valid email! ")
    @Length(min = 3, message = "Email cannot be empty!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
