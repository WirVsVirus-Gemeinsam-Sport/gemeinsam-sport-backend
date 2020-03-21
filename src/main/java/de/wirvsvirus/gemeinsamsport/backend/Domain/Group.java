package de.wirvsvirus.gemeinsamsport.backend.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Entity
@Table(name = "WVV_Group")
public class Group extends AbstractEntity {

    @NotNull
    @NonNull
    private String url;
}
