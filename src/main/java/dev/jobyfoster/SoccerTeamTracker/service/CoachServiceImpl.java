package dev.jobyfoster.SoccerTeamTracker.service;

import dev.jobyfoster.SoccerTeamTracker.dto.requestDto.CoachRequestDto;
import dev.jobyfoster.SoccerTeamTracker.model.Coach;
import dev.jobyfoster.SoccerTeamTracker.model.Team;
import dev.jobyfoster.SoccerTeamTracker.repository.CoachRepository;
import dev.jobyfoster.SoccerTeamTracker.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoachServiceImpl implements CoachService {


    private final CoachRepository coachRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;

    @Autowired
    public CoachServiceImpl(CoachRepository coachRepository, TeamRepository teamRepository, TeamService teamService) {
        this.coachRepository = coachRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    @Override
    public Coach addCoach(CoachRequestDto coachRequestDto) {
        Coach coach = new Coach();
        coach.setName(coachRequestDto.getName());
        if(coachRequestDto.getTeams() == null) {
            return coachRepository.save(coach);
        }

        for(Team team: coachRequestDto.getTeams()) {
            coach.addTeam(teamService.getTeam(team.getId()));
        }

        return coachRepository.save(coach);
    }

    @Override
    public List<Coach> getCoaches() {
        List<Coach> coaches = new ArrayList<>();
        coachRepository.findAll().forEach(coaches::add);
        return coaches;
    }

    @Override
    public Coach getCoach(Long coachId) {
        return coachRepository.findById(coachId).orElseThrow(
                () -> new IllegalArgumentException("Coach with coachId " + coachId + " does not exist")
        );
    }

    @Override
    public void deleteCoach(Long coachId) {
        Coach coach = getCoach(coachId);
        coachRepository.delete(coach);
    }

    @Override
    public Coach editCoach(Long coachId, CoachRequestDto coachRequestDto) {
        Coach coachToEdit = getCoach(coachId);
        coachToEdit.getTeams().clear();
        for (Team team: coachRequestDto.getTeams()) {
            coachToEdit.addTeam(team);
        }

        coachToEdit.setName(coachRequestDto.getName());

        return coachRepository.save(coachToEdit);
    }

    @Override
    @Transactional
    public Coach updateCoachTeams(Long coachId, Set<Long> teamIds) {
        Coach coach = coachRepository.findById(coachId).orElseThrow(() -> new IllegalArgumentException("Coach with ID " + coachId + " not found"));

        coach.getTeams().clear();
        coachRepository.save(coach);

        Set<Team> updatedTeams = new HashSet<>((Collection) teamRepository.findAllById(teamIds));
        for (Team team : updatedTeams) {
            coach.addTeam(team);
        }

        return coachRepository.save(coach);
    }

}
