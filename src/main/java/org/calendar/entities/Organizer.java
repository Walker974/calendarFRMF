package org.calendar.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "organizer_type_id", nullable = false)
    private OrganizerType organizerType;
    private String logo;
    private String color;
}
