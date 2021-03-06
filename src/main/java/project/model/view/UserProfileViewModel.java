package project.model.view;

import project.model.service.RoleServiceModel;

import java.util.HashSet;
import java.util.Set;

public class UserProfileViewModel extends BaseViewModel {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String createDate;
    private Set<MovieViewModel> watchList = new HashSet<>();
    private Set<RoleServiceModel> authorities = new HashSet<>();

    public UserProfileViewModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateDate() { return createDate; }

    public void setCreateDate(String createDate) { this.createDate = createDate; }

    public Set<MovieViewModel> getWatchList() {
        return watchList;
    }

    public void setWatchList(Set<MovieViewModel> watchList) {
        this.watchList = watchList;
    }

    public Set<RoleServiceModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<RoleServiceModel> authorities) {
        this.authorities = authorities;
    }
}
