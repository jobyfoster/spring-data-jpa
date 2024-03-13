package dev.jobyfoster.SoccerTeamTracker.dto.responseDto;

import dev.jobyfoster.SoccerTeamTracker.model.Coach;
import dev.jobyfoster.SoccerTeamTracker.model.Player;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TeamResponseDto {
    private Long id;
    private String name;
    private Set<Coach> coaches;
    private List<String> playerNames;
}
