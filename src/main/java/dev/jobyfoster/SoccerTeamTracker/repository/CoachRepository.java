package dev.jobyfoster.SoccerTeamTracker.repository;

import dev.jobyfoster.SoccerTeamTracker.model.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends CrudRepository<Coach, Long> {
}
