package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.TeamRepository;
import org.springframework.stereotype.Service;
import org.calendar.entities.Team;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public Team createTeam(Team team) {
        if (team.getName() == null || team.getName().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be null or empty");
        }
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
