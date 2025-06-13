package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.CompetitionRepository;
import org.calendar.entities.Competition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository competitionRepository;

    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public Competition createCompetition(Competition competition) {
        if (competition.getName() == null || competition.getName().isEmpty()) {
            throw new IllegalArgumentException("Competition name must not be empty");
        }
        return competitionRepository.save(competition);
    }

    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Competition not found with ID: " + id));
    }

}
