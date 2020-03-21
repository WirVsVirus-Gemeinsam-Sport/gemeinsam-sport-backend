package de.wirvsvirus.gemeinsamsport.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class AccountRequest {

    @NonNull
    private String username;
}
