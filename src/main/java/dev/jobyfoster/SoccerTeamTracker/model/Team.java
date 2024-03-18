package dev.jobyfoster.SoccerTeamTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Team")
public class Team {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "teams", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Coach> coaches = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;

    public Team(String name) {
        this.name = name;
    }

    public void addCoach(Coach coach) {
        this.coaches.add(coach);
        coach.getTeams().add(this);
    }

    public void removeCoach(Coach coach) {
        this.coaches.remove(coach);
        coach.getTeams().remove(this);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
