package de.wirvsvirus.gemeinsamsport.backend.Dao;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id);

    Collection<T> getAll();

    void save(@NotNull T t);

    void update(@NotNull T t);

    void delete(@NotNull T t);
}
