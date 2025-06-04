package org.calendar.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StadiumEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @Column(nullable = false)
    private String eventName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;
}
