package de.wirvsvirus.gemeinsamsport.backend.Controller.Rest;

import de.wirvsvirus.gemeinsamsport.backend.Dao.AppointmentDao;
import de.wirvsvirus.gemeinsamsport.backend.Dao.GroupDao;
import de.wirvsvirus.gemeinsamsport.backend.Dto.AppointmentInfo;
import de.wirvsvirus.gemeinsamsport.backend.Entity.Appointment;
import de.wirvsvirus.gemeinsamsport.backend.Entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private GroupDao groupDao;

    @GetMapping("/group/{groupId}/appointments")
    public Collection<AppointmentInfo> getAppointmentsByGroup(@PathVariable("groupId") long groupId) {
        Optional<Group> maybeGroup = groupDao.findById(groupId);
        if (maybeGroup.isPresent()) {
            return maybeGroup.get()
                    .getAppointments()
                    .stream()
                    .map(appointment -> new AppointmentInfo(appointment.getId(), appointment.getGroup().getId(), appointment.getDate()))
                    .collect(Collectors.toList());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist");
    }

    @GetMapping("/group/{groupId}/appointments/{appointmentId}")
    public AppointmentInfo getAppointment(@PathVariable("groupId") long groupId, @PathVariable("appointmentId") long appointmentId) {
        Optional<Appointment> maybeAppointment = appointmentDao.findById(appointmentId);
        if (maybeAppointment.isPresent() && groupId == maybeAppointment.get().getGroup().getId()) {
            Appointment appointment = maybeAppointment.get();
            return new AppointmentInfo(appointment.getId(), appointment.getGroup().getId(), appointment.getDate());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This appointment does not exist");
    }

    @PostMapping("/group/{groupId}/appointments/")
    public long createAppointment(@PathVariable("groupId") long groupId, @RequestBody AppointmentInfo appointmentInfo) {
        Optional<Group> maybeGroup = groupDao.findById(groupId);
        if (maybeGroup.isPresent()) {
            Appointment appointment = new Appointment();
            appointment.setGroup(maybeGroup.get());
            appointment.setDate(appointmentInfo.getDate());
            appointmentDao.save(appointment);
            return appointment.getId();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist");
    }
}
