package project.service;

import project.model.entity.Actor;
import project.model.service.ActorServiceModel;
import project.model.view.ActorViewModel;

import java.io.IOException;
import java.util.List;

public interface ActorService {
    void addActor(ActorServiceModel actorServiceModel) throws IOException;

    List<ActorViewModel> findAllActors();

    ActorViewModel findById(String id);

    void delete(String id);

    Actor findByName(String name);
}
