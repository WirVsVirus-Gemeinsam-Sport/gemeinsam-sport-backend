package de.wirvsvirus.gemeinsamsport.backend.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "WVV_WORKOUT_STEP")
public class WorkoutStep extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @NonNull
    @NotNull
    @Length(max = 32)
    private String description;

    @NonNull
    @NotNull
    private Integer duration;
}
