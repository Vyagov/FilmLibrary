package project.service;

import project.model.entity.Role;
import project.model.service.NewRolesServiceModel;

import java.util.List;

public interface RoleService {

    void editRoles(NewRolesServiceModel newRolesServiceModel);

    List<Role> getRoles();

    Role findByAuthority(String authority);
}
