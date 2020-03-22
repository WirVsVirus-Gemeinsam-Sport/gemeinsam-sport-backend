package de.wirvsvirus.gemeinsamsport.backend.Dao;

import de.wirvsvirus.gemeinsamsport.backend.Entity.WorkoutStep;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutStepDao extends CrudRepository<WorkoutStep, Long> {
}
