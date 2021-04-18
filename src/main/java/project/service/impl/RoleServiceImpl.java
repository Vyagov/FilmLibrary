package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.error.UserIsNotExistException;
import project.model.entity.Role;
import project.model.entity.User;
import project.model.service.NewRolesServiceModel;
import project.repository.RoleRepository;
import project.repository.UserRepository;
import project.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void editRoles(NewRolesServiceModel newRolesServiceModel) {
        User user = this.findUserById(newRolesServiceModel.getUserId());

        if (user == null) {
            throw new UserIsNotExistException("User is not Exist!");
        }
        user.getAuthorities().clear();

        Role userRole = new Role("USER");
        user.getAuthorities().add(userRole);

        if (newRolesServiceModel.getRoleAdmin().equals("1")) {
            Role adminRole = new Role("ADMIN");
            user.getAuthorities().add(adminRole);
        }
        this.createUser(user);
    }

    @Override
    public Role findByAuthority(String authority) {
        return this.roleRepository.findByAuthority(authority).orElse(null);
    }

    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }

    private User findUserById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }

    private void createUser(User user) {
        this.userRepository.saveAndFlush(user);
    }
}
