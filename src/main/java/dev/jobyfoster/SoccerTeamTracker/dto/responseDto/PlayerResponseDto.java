package dev.jobyfoster.SoccerTeamTracker.dto.responseDto;

import dev.jobyfoster.SoccerTeamTracker.model.Team;
import lombok.Data;

@Data
public class PlayerResponseDto {
    private Long id;
    private String name;
    private Team team;
}
