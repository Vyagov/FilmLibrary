package project.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import project.model.service.UserServiceModel;
import project.model.view.UserProfileUpdateViewModel;
import project.model.view.UserProfileViewModel;

public interface UserService extends UserDetailsService {

    void register(UserServiceModel userServiceModel);

    UserProfileViewModel findByUsernameForViewProfile(String username);

    UserProfileUpdateViewModel findByUsernameForUpdateProfile(String username);

    void updateProfile(UserServiceModel userServiceModel);

    void deleteProfile(String id);
}
