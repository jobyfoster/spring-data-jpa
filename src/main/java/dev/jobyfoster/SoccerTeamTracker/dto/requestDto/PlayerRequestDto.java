package dev.jobyfoster.SoccerTeamTracker.dto.requestDto;

import dev.jobyfoster.SoccerTeamTracker.model.Team;
import lombok.Data;

@Data
public class PlayerRequestDto {
    private String name;
    private Team team;
}
