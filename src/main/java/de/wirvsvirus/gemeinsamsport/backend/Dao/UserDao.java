package de.wirvsvirus.gemeinsamsport.backend.Dao;

import de.wirvsvirus.gemeinsamsport.backend.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
