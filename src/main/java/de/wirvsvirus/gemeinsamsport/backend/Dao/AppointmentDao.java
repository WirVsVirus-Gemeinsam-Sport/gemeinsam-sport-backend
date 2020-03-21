package de.wirvsvirus.gemeinsamsport.backend.Dao;

import de.wirvsvirus.gemeinsamsport.backend.Entity.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDao extends CrudRepository<Appointment, Long> {
}
