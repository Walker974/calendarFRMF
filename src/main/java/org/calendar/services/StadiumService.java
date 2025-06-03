package org.calendar.services;

import lombok.RequiredArgsConstructor;
import org.calendar.dao.StadiumRepository;
import org.calendar.entities.Stadium;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    public Stadium createStadium(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }

    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }
}
