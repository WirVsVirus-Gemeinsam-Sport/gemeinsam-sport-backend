package de.wirvsvirus.gemeinsamsport.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "WVV_Group")
public class Group extends AbstractEntity {

    @NotNull
    @NonNull
    @Length(max = 32)
    @Column(unique = true)
    private String name;

    @Length(max = 1024)
    private String description;

    @NotNull
    @NonNull
    @Column(nullable = false, unique = true)
    private String url;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<WorkoutStep> workoutSteps;
}
