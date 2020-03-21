package de.wirvsvirus.gemeinsamsport.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class GroupInfo {

    private long id;

    @NonNull
    private String name;

    private String description;

    @NonNull
    private String url;
}
