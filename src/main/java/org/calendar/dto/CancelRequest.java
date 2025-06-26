package org.calendar.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CancelRequest(@NotNull @Size(min=1, max=100, message = "Reason must be between 1 and 100 characters")
                            String reason) {
}
