package dev.jobyfoster.SoccerTeamTracker.dto.responseDto;

import dev.jobyfoster.SoccerTeamTracker.model.Team;
import lombok.Data;

import java.util.Set;

@Data
public class CoachResponseDto {
    private Long id;
    private String name;
    private Set<Team> teams;
}
