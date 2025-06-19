package org.calendar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.calendar.entities.Stadium;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    @Query("SELECT s FROM Stadium s WHERE s.city.id = ?1")
    List<Stadium> findByCityId(Long cityId);
}
