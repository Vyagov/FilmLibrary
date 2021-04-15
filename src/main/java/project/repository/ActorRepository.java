package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.entity.Actor;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, String> {
    List<Actor> findAll();

    Optional<Actor> findById(String id);

    void deleteById(String id);

    Optional<Actor> findByName(String name);
}
