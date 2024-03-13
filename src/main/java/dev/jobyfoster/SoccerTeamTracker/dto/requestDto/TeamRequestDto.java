package dev.jobyfoster.SoccerTeamTracker.dto.requestDto;

import dev.jobyfoster.SoccerTeamTracker.model.Coach;
import dev.jobyfoster.SoccerTeamTracker.model.Player;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TeamRequestDto {
    private String name;
    private Set<Coach> coaches;
    private List<Player> players;
}
