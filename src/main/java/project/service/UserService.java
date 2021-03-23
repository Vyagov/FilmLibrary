package project.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import project.model.service.UserServiceModel;

public interface UserService extends UserDetailsService {

    UserServiceModel register(UserServiceModel userServiceModel);
}
