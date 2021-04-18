package project.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import project.model.service.UserServiceModel;
import project.model.view.MovieViewModel;
import project.model.view.UserProfileUpdateViewModel;
import project.model.view.UserProfileViewModel;

import java.util.List;

public interface UserService extends UserDetailsService {

    void register(UserServiceModel userServiceModel);

    UserProfileViewModel findByUsernameForViewProfile(String username);

    UserProfileUpdateViewModel findByUsernameForUpdateProfile(String username);

    void updateProfile(UserServiceModel userServiceModel);

    void deleteProfile(String id);

    List<MovieViewModel> getWatchList(String username);

    void addMovieInWatchList(String username, MovieViewModel movieViewModel);

    List<UserProfileViewModel> getAllUsersForView();

    UserServiceModel findUserById(String userId);
}
