package project.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.annotation.Title;
import project.error.CustomBaseException;
import project.error.UserIsNotExistException;
import project.model.binding.NewRolesBindingModel;
import project.model.service.NewRolesServiceModel;
import project.model.service.UserServiceModel;
import project.model.view.UserProfileViewModel;
import project.service.RoleService;
import project.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, ModelMapper modelMapper, RoleService roleService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Title(name = "Admin")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAIN_ADMIN')")
    @GetMapping("/user/all")
    public String getAllUsers(Model model) {
        List<UserProfileViewModel> allUsers = this.userService.getAllUsersForView();

        model.addAttribute("allUsers", allUsers);

        return "admin/all-users";
    }

    @Title(name = "Admin")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN')")
    @GetMapping("/edit/roles/{userId}")
    public String editUserRoles(@PathVariable("userId") String userId, Model model) {
        UserServiceModel userForEdit = this.userService.findUserById(userId);

        model.addAttribute("userForEdit", userForEdit);

        return "admin/edit-roles";
    }

    @Title(name = "Admin")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN')")
    @PostMapping("/user/role-save")
    public String saveNewRole(@ModelAttribute("newRolesBindingModel") NewRolesBindingModel newRolesBindingModel) {
        NewRolesServiceModel newRolesServiceModel = this.modelMapper.map(newRolesBindingModel, NewRolesServiceModel.class);

        this.roleService.editRoles(newRolesServiceModel);

        return "redirect:/admin/user/all";
    }

    @Title(name = "Admin")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAIN_ADMIN')")
    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        this.userService.deleteProfile(id);

        return "redirect:/admin/user/all";
    }

    @ExceptionHandler({UserIsNotExistException.class})
    public ModelAndView handleAdminException(CustomBaseException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msgError", e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
