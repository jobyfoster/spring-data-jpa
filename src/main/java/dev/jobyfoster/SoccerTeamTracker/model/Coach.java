package dev.jobyfoster.SoccerTeamTracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(id, coach.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
