package dev.jobyfoster.SoccerTeamTracker.repository;

import dev.jobyfoster.SoccerTeamTracker.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

}
