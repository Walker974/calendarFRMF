package org.calendar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.calendar.entities.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
