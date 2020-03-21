package de.wirvsvirus.gemeinsamsport.backend.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "WVV_Appointment")
public class Appointment extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @NotNull
    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
