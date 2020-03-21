package de.wirvsvirus.gemeinsamsport.backend.Dao;

import de.wirvsvirus.gemeinsamsport.backend.Entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends CrudRepository<Group, Long> {
}
