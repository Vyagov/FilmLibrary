package project.web;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.model.binding.AjaxBindingModel;
import project.model.view.AjaxViewModel;
import project.model.view.MovieViewModel;
import project.service.MovieService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AjaxController {
    private static final String REGEX = "^[a-zA-Z0-9-\\s+,.!'\"_\\p{IsCyrillic}]+$";
    private final MovieService movieService;

    public AjaxController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/search")
    public AjaxViewModel getSearchResultWithAjax(@RequestBody AjaxBindingModel ajaxBindingModel) {
        AjaxViewModel result = new AjaxViewModel();
        String search = ajaxBindingModel.getMovieTitle();

        if (isValid(ajaxBindingModel)) {
            List<MovieViewModel> movies = this.movieService.getAllMovies()
                    .stream().filter(movie -> movie.getMovieTitle().contains(search))
                    .collect(Collectors.toList());

            if (movies.size() > 0) {
                result.setMessage("");
                result.setMovies(movies);
            } else {
                result.setMessage("There are no movies with this title!");
            }
        } else {
            result.setMessage("Invalid search criteria!");
        }
        return result;
    }

    private boolean isValid(AjaxBindingModel ajaxBindingModel) {
        return (ajaxBindingModel != null) && (ajaxBindingModel.getMovieTitle().matches(REGEX));
    }
}
