package org.calendar.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import org.mapstruct.ReportingPolicy;
import org.calendar.dto.OrganizerDto;
import org.calendar.entities.Organizer;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizerMapper {

    @Named("toDto")
    default OrganizerDto toDto(Organizer organizer) {
        if (organizer == null) {
            return null;
        }
        return new OrganizerDto(
                organizer.getId(),
                organizer.getEmail(),
                organizer.getName(),
                organizer.getPhoneNumber(),
                organizer.getOrganizerType(),
                organizer.getLogo(),
                organizer.getColor()
        );
    }

    @Named("toEntity")
    default Organizer toEntity(OrganizerDto dto) {
        if (dto == null) {
            return null;
        }
        return new Organizer(
                dto.id(),
                dto.email(),
                dto.name(),
                dto.phoneNumber(),
                dto.organizerType(),
                dto.logo(),
                dto.color()
        );
    }

    @IterableMapping(qualifiedByName = "toDto")
    List<OrganizerDto> toDtoList(List<Organizer> organizers);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Organizer> toEntityList(List<OrganizerDto> dtos);
}