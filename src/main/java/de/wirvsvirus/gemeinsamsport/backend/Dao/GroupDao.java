package de.wirvsvirus.gemeinsamsport.backend.Dao;

import de.wirvsvirus.gemeinsamsport.backend.Domain.Group;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class GroupDao implements Dao<Group> {
    private Map<Long, Group> storage = new HashMap<>();
    private long nextId = 1;

    @Override
    public Optional<Group> get(long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Collection<Group> getAll() {
        return storage.values();
    }

    @Override
    public void save(@NotNull Group group) {
        // TODO ensure group does not exist yet, validate group
        group.setId(nextId++);
        storage.put(group.getId(), group);
    }

    @Override
    public void update(@NotNull Group group) {
        // TODO ensure group exists, validate group
        storage.replace(group.getId(), group);
    }

    @Override
    public void delete(@NotNull Group group) {
        // TODO ensure group exists
        storage.remove(group.getId());
    }
}
