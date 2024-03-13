package dev.jobyfoster.SoccerTeamTracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@Table(name = "Coach")
public class Coach {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "coach_team",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    public Coach(String name) {
        this.name = name;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
        team.getCoaches().add(this);
    }


    public void removeTeam(Team team) {
        this.teams.remove(team);
        team.getCoaches().remove(this);
    }
}
