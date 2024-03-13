package dev.jobyfoster.SoccerTeamTracker.service;

import dev.jobyfoster.SoccerTeamTracker.dto.requestDto.CoachRequestDto;
import dev.jobyfoster.SoccerTeamTracker.model.Coach;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoachService {

    Coach addCoach(CoachRequestDto coachRequestDto);

    List<Coach> getCoaches();

    Coach getCoach(Long coachId);

    void deleteCoach(Long coachId);

    Coach editCoach(Long coachId, CoachRequestDto coachRequestDto);
}