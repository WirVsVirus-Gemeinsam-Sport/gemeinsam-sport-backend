package de.wirvsvirus.gemeinsamsport.backend.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "WVV_User")
public class User extends AbstractEntity {

    @NotNull
    @NonNull
    @Length(max = 32)
    @Column(unique = true)
    private String username;

    @Length(max = 128)
    private String token;
}
