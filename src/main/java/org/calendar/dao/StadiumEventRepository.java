package org.calendar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.calendar.entities.Stadium;
import org.calendar.entities.StadiumEvent;
import java.util.List;

public interface StadiumEventRepository extends JpaRepository<StadiumEvent, Long> {
    List<StadiumEvent> findByStadium(Stadium stadium);
}
