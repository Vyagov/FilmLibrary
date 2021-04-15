package project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.model.entity.Actor;
import project.model.entity.Movie;
import project.model.service.MovieServiceModel;
import project.model.view.MovieViewModel;
import project.repository.MovieRepository;
import project.service.ActorService;
import project.service.CloudinaryService;
import project.service.MovieService;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovieServiceImpl implements MovieService {
    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;
    private final CloudinaryService cloudinaryService;
    private final ActorService actorService;

    @Autowired
    public MovieServiceImpl(ModelMapper modelMapper,
                            MovieRepository movieRepository,
                            CloudinaryService cloudinaryService,
                            ActorService actorService) {
        this.modelMapper = modelMapper;
        this.movieRepository = movieRepository;
        this.cloudinaryService = cloudinaryService;
        this.actorService = actorService;
    }

    @Override
    public void addMovie(MovieServiceModel movieServiceModel) throws IOException {
        Movie movie = this.modelMapper.map(movieServiceModel, Movie.class);

        Stream.of(movieServiceModel.getActorNames().trim().split(","))
                .collect(Collectors.toSet())
                .forEach(name -> movie.getCast().add(this.actorService.findByName(name)));

        MultipartFile image = movieServiceModel.getImage();

        if (!image.isEmpty()) {
            String imgUrl = this.cloudinaryService.uploadImage(image);
            movie.setPathToPoster(imgUrl);
        } else {
            movie.setPathToPoster("/images/default_movie.jpg");
        }
        this.movieRepository.saveAndFlush(movie);
    }

    @Override
    public List<MovieServiceModel> findAllMovies() {
        return this.movieRepository.findAll()
                .stream()
                .map(this::mapToService)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieViewModel> getAllMovies() {
        return this.movieRepository.findAll()
                .stream()
                .map(movie -> this.modelMapper.map(movie, MovieViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public MovieViewModel findById(String id) {
        return this.movieRepository
                .findById(id)
                .map(this::mapToView)
                .orElse(null);
    }

    @Override
    public void delete(String id) {

    }

    private MovieServiceModel mapToService(Movie movie) {
        return this.modelMapper.map(movie, MovieServiceModel.class);
    }

    private MovieViewModel mapToView(Movie movie) {
        return this.modelMapper.map(movie, MovieViewModel.class);
    }
}
