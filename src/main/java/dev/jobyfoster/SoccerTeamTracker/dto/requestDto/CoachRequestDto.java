package dev.jobyfoster.SoccerTeamTracker.dto.requestDto;

import dev.jobyfoster.SoccerTeamTracker.model.Team;
import lombok.Data;

import java.util.Set;

@Data
public class CoachRequestDto {
    private String name;
    private Set<Team> teams;
}
