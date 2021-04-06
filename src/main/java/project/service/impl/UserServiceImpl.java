package project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.error.*;
import project.model.entity.Role;
import project.model.entity.User;
import project.model.service.UserServiceModel;
import project.model.view.UserProfileUpdateViewModel;
import project.model.view.UserProfileViewModel;
import project.repository.UserRepository;
import project.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public void register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        User saved = this.getUserByUsername(userServiceModel.getUsername());

        if (saved != null) {
            throw new UsernameAlreadyExistException("User with username '" + saved.getUsername() + "' already exists!");
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
    }

    @Override
    public UserProfileViewModel findByUsernameForViewProfile(String username) {
        UserProfileViewModel userProfileViewModel = this.mapToView(this.getUserByUsername(username));

        String createDate = userProfileViewModel.getCreateDate();
        LocalDateTime localDateTime = LocalDateTime.parse(createDate, DateTimeFormatter.ISO_DATE_TIME);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        userProfileViewModel.setCreateDate(localDateTime.format(formatter));

        return userProfileViewModel;
    }

    @Override
    public UserProfileUpdateViewModel findByUsernameForUpdateProfile(String username) {
        return this.mapToUpdate(this.getUserByUsername(username));
    }

    @Override
    public void updateProfile(UserServiceModel userServiceModel) {
        User user = this.getUserByUsername(userServiceModel.getUsername());
        User saved = this.getUserByEmail(userServiceModel.getEmail());

        if (saved != null && !user.getUsername().equals(saved.getUsername())) {
            throw new EmailAlreadyExistException("User with email '" + userServiceModel.getEmail() + "' already exists!");
        }
        user.setEmail(userServiceModel.getEmail());
        user.setFirstName(userServiceModel.getFirstName());
        user.setLastName(userServiceModel.getLastName());

        try {
            createUser(user);
        } catch (Exception ignored) {
            throw new UserRegistrationException("Cannot register user with username " + user.getUsername());
        }
    }

    @Override
    public void deleteProfile(String id) {
        User user = this.getUserById(id);

//        if (user == null) {
//            throw new UserIdNotFoundException("User with this Id is not found!");
//        }
//        if (user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("MAIN_ADMIN"))) {
//            throw new UserIsNotHaveAccessException("Do not have access to delete MAIN ADMIN!");
//        }
        this.userRepository.deleteById(id);
    }

    private User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    private User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    private User getUserById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }

    private void createUser(User user) {

        this.userRepository.saveAndFlush(user);
    }

    private UserProfileViewModel mapToView(User user) {
        return this.modelMapper.map(user, UserProfileViewModel.class);
    }

    private UserProfileUpdateViewModel mapToUpdate(User user) {
        return this.modelMapper.map(user, UserProfileUpdateViewModel.class);
    }

}
