package de.wirvsvirus.gemeinsamsport.backend.Controller.Rest;

import de.wirvsvirus.gemeinsamsport.backend.Dao.GroupDao;
import de.wirvsvirus.gemeinsamsport.backend.Domain.Group;
import de.wirvsvirus.gemeinsamsport.backend.Dto.GroupInfo;
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
public class GroupController {

    @Autowired
    private GroupDao groupDao;

    @GetMapping("/group/")
    public Collection<GroupInfo> getAllGroups() {
        return groupDao.getAll().stream()
                .map(group -> new GroupInfo(group.getId(), group.getUrl()))
                .collect(Collectors.toList());
    }

    @GetMapping("/group/{id}")
    public GroupInfo getGroup(@PathVariable("id") long id) {
        Optional<Group> group = groupDao.get(id);
        if (group.isPresent()) {
            return new GroupInfo(id, group.get().getUrl());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist");
    }

    @PostMapping("/group/")
    public long putGroup(@RequestBody GroupInfo groupInfo) {
        Group group = new Group(null, groupInfo.getUrl());
        groupDao.save(group);
        return group.getId();
    }
}
