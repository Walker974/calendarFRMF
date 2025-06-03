package org.calendar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.calendar.entities.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
}
