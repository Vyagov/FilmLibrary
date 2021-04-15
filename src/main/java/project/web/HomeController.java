package project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.model.view.MovieViewModel;
import project.service.MovieService;

import java.util.List;

@Controller
public class HomeController {
private final MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<MovieViewModel> allMovies = this.movieService.getAllMovies();
        model.addAttribute("allMovies", allMovies);
        return "home";
    }
}
