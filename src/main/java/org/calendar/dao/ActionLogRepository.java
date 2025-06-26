package org.calendar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.calendar.entities.ActionLog;

public interface ActionLogRepository extends JpaRepository<ActionLog, Long> {
}
