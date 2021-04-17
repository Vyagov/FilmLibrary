package project.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.annotation.Title;
import project.components.MovieImageValidator;
import project.model.binding.MovieBindingModel;
import project.model.service.MovieServiceModel;
import project.model.view.MovieViewModel;
import project.service.ActorService;
import project.service.MovieService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ActorService actorService;
    private final ModelMapper modelMapper;
    private final MovieImageValidator movieImageValidator;

    public MovieController(MovieService movieService,
                           ActorService actorService,
                           ModelMapper modelMapper,
                           MovieImageValidator movieImageValidator) {
        this.movieService = movieService;
        this.actorService = actorService;
        this.modelMapper = modelMapper;
        this.movieImageValidator = movieImageValidator;
    }

    @Title(name = "Add Movie")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN','ADMIN')")
    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("actors")) {
            model.addAttribute("actors", this.actorService.findAllActors());
        }
        if (!model.containsAttribute("movieBindingModel")) {
            model.addAttribute("movieBindingModel", new MovieBindingModel());
        }
        return "movie/add";
    }

    @Title(name = "Add Movie")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN','ADMIN')")
    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("movieBindingModel") MovieBindingModel movieBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {
        this.movieImageValidator.validate(movieBindingModel, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.movieBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("movieBindingModel", movieBindingModel);

            return "redirect:/movies/add";
        }
        MovieServiceModel movieServiceModel = this.modelMapper.map(movieBindingModel, MovieServiceModel.class);

        this.movieService.addMovie(movieServiceModel);

        return "redirect:/home";
    }

    @Title(name = "Movie Details")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN','ADMIN', 'USER')")
    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") String id, ModelAndView modelAndView) {
        MovieViewModel movie = this.movieService.findById(id);

        modelAndView.addObject("movie", movie);
        modelAndView.setViewName("movie/details");

        return modelAndView;
    }
}
