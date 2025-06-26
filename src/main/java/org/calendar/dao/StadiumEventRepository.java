package org.calendar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.calendar.entities.StadiumEvent;
import java.time.LocalDateTime;
import java.util.List;

public interface StadiumEventRepository extends JpaRepository<StadiumEvent, Long> {
    List<StadiumEvent> findByStadiumId(Long stadiumId);
    List<StadiumEvent> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
