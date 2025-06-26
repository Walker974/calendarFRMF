package org.calendar.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.calendar.enums.StadiumEventStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "stadium_events")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
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
    @JoinColumn(name = "organizer_id", nullable = false)
    private Organizer organizer;
    @ManyToOne
    @JoinColumn(name = "event_type_id", nullable = false)
    private EventType eventType;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private StadiumEventStatus status;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
}
