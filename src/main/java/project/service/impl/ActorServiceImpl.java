package project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.model.entity.Actor;
import project.model.service.ActorServiceModel;
import project.model.view.ActorViewModel;
import project.repository.ActorRepository;
import project.service.ActorService;
import project.service.CloudinaryService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository,
                            ModelMapper modelMapper,
                            CloudinaryService cloudinaryService) {
        this.actorRepository = actorRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void addActor(ActorServiceModel actorServiceModel) throws IOException {
        Actor actor = this.modelMapper.map(actorServiceModel, Actor.class);

        MultipartFile image = actorServiceModel.getImage();

        if (!image.isEmpty()) {
            String imgUrl = this.cloudinaryService.uploadImage(image);
            actor.setPathToImage(imgUrl);
        } else {
            actor.setPathToImage("/images/default_actor.png");
        }
        this.actorRepository.saveAndFlush(actor);
    }

    @Override
    public List<ActorViewModel> findAllActors() {
        return this.actorRepository
                .findAll()
                .stream()
                .map(this::actorToView)
                .collect(Collectors.toList());
    }

    @Override
    public ActorViewModel findById(String id) {
        return this.actorRepository
                .findById(id)
                .map(this::actorToView)
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        this.actorRepository.deleteById(id);
    }

    @Override
    public Actor findByName(String name) {
        return this.actorRepository
                .findByName(name)
                .orElse(null);
    }

    private ActorViewModel actorToView(Actor actor) {
        return this.modelMapper.map(actor, ActorViewModel.class);
    }

}
