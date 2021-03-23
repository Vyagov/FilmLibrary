package project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.error.UserRegistrationException;
import project.error.UsernameAlreadyExistException;
import project.model.entity.Role;
import project.model.entity.User;
import project.model.service.UserServiceModel;
import project.repository.UserRepository;
import project.service.UserService;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User does not exists!");
        }
        return user;
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        User saved = this.getUserByUsername(userServiceModel.getUsername());

        if (saved != null) {
            throw new UsernameAlreadyExistException("User with username " + saved.getUsername() + " already exists!");
        }
        if (this.userRepository.count() == 0) {
            Role mainAdmin = new Role("MAIN_ADMIN");
            Role adminRole = new Role("ADMIN");
            Role userRole = new Role("USER");

            user.setAuthorities(Set.of(mainAdmin, adminRole, userRole));
        } else {
            Role userRole = new Role("USER");

            user.setAuthorities(Set.of(userRole));
        }
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        try {
            createUser(user);
        } catch (Exception ignored) {
            throw new UserRegistrationException("Cannot register user with username " + user.getUsername());
        }
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    private User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    private void createUser(User user) {
        this.userRepository.saveAndFlush(user);
    }
}
