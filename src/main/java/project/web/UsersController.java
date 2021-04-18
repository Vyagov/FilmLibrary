package project.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.annotation.Title;
import project.error.*;
import project.model.binding.UserProfileUpdateBindingModel;
import project.model.binding.UserRegisterBindingModel;
import project.model.service.MovieServiceModel;
import project.model.service.UserServiceModel;
import project.model.view.MovieViewModel;
import project.service.MovieService;
import project.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final MovieService movieService;

    @Autowired
    public UsersController(UserService userService, ModelMapper modelMapper, MovieService movieService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.movieService = movieService;
    }

    @Title(name = "User Login")
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/login-error")
    public ModelAndView loginError(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                   ModelAndView modelAndView) {
        modelAndView.addObject("error", "bad.credentials");
        modelAndView.addObject("username", username);
        modelAndView.setViewName("auth/login");

        return modelAndView;
    }

    @Title(name = "User Register")
    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel")
                                          UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            return "redirect:/users/register";
        }
        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:/users/login";
    }

    @Title(name = "User Profile")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MAIN_ADMIN')")
    @GetMapping("/profile")
    public ModelAndView userProfile(ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("userView", this.userService.findByUsernameForViewProfile(principal.getName()));
        modelAndView.setViewName("profile/view");
        return modelAndView;
    }

    @Title(name = "Profile Update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MAIN_ADMIN')")
    @GetMapping("/profile/update")
    public String userUpdateProfile(Principal principal, Model model) {
        if (!model.containsAttribute("userProfileUpdateBindingModel")) {
            model.addAttribute("userProfileUpdateBindingModel", this.userService.findByUsernameForUpdateProfile(principal.getName()));
        }
        return "profile/update";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MAIN_ADMIN')")
    @PostMapping("/profile/update")
    public String userUpdateProfileConfirm(@Valid @ModelAttribute("userProfileUpdateBindingModel")
                                                   UserProfileUpdateBindingModel userProfileUpdateBindingModel,
                                           BindingResult bindingResult,
                                           RedirectAttributes redirectAttributes,
                                           Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfileUpdateBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("userProfileUpdateBindingModel", userProfileUpdateBindingModel);
            return "redirect:/users/profile/update";
        }
        userProfileUpdateBindingModel.setUsername(principal.getName());

        this.userService.updateProfile(this.modelMapper.map(userProfileUpdateBindingModel, UserServiceModel.class));
        return "redirect:/users/profile";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAIN_ADMIN', 'USER')")
    @GetMapping("/profile/delete/{id}")
    public String delete(@PathVariable("id") String id, HttpSession session) {
        this.userService.deleteProfile(id);
        session.invalidate();

        return "redirect:/";
    }

    @Title(name = "Watch List")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MAIN_ADMIN')")
    @GetMapping("/watchList")
    public String userWatchList(Principal principal, Model model) {
        if (!model.containsAttribute("watchList")) {
            model.addAttribute("watchList", this.userService.getWatchList(principal.getName()));
        }
        return "profile/watch-list";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MAIN_ADMIN')")
    @GetMapping("/watchList/add/{id}")
    public String userWatchListAdd(@PathVariable("id") String id, Principal principal) {
        MovieViewModel movieViewModel = this.movieService.findById(id);

        this.userService.addMovieInWatchList(principal.getName(), movieViewModel);

        return "redirect:/";
    }

    @ExceptionHandler({UsernameAlreadyExistException.class,
            UserIsNotHaveAccessException.class,
//            UserIdNotFoundException.class,
            MovieAlreadyInWatchListException.class,
            EmailAlreadyExistException.class,
            UsernameNotFoundException.class,
            UserRegistrationException.class})
    public ModelAndView handleUserException(CustomBaseException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
