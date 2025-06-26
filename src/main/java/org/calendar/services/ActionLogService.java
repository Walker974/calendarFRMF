package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.ActionLogRepository;
import org.calendar.enums.ActionType;
import org.springframework.stereotype.Service;
import org.calendar.entities.ActionLog;
import org.calendar.entities.User;

@Service
@RequiredArgsConstructor
public class ActionLogService {

    private final ActionLogRepository actionLogRepository;

    public void log(ActionType actionType, User user) {
        actionLogRepository.save(ActionLog.builder()
                .actionType(actionType)
                .user(user)
                .timestamp(java.time.LocalDateTime.now())
                .build());
    }
}
