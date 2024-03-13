package dev.jobyfoster.SoccerTeamTracker.repository;

import dev.jobyfoster.SoccerTeamTracker.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
