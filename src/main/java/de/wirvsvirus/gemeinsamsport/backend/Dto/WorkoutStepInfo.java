package de.wirvsvirus.gemeinsamsport.backend.Dto;

import de.wirvsvirus.gemeinsamsport.backend.Entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
public class WorkoutStepInfo {

    private long id;

    private long groupId;

    @NonNull
    private String description;

    @NonNull
    private Integer duration;
}
