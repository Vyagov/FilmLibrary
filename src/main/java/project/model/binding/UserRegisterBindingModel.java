package project.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterBindingModel extends BaseBindingModel{
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    @NotNull
    @Length(min = 2, max = 35, message = "Name length must be between 2 and 35 characters! ")
    @Pattern(regexp = "^[a-zA-Z\\p{IsCyrillic}]+$", message = "Name must be only letters!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Length(min = 2, max = 35, message = "Name length must be between 2 and 35 characters! ")
    @Pattern(regexp = "^[a-zA-Z\\p{IsCyrillic}]+$", message = "Name must be only letters!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Length(min = 3, max = 20, message = "Username length must be between 3 and 20 characters! ")
    @Pattern(regexp = "^[a-zA-Z0-9\\s+,.!\"-_]+$", message = "Username is not valid!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Email(message = "Enter valid email! ")
    @Length(min = 3, message = "Email cannot be empty!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Length(min = 3, max = 20, message = "Passwords not match!")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
