package de.wirvsvirus.gemeinsamsport.backend.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class AppointmentInfo {

    private long id;

    private long groupId;

    @NonNull
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date date;
}
