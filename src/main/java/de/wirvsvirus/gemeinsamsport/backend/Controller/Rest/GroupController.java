package de.wirvsvirus.gemeinsamsport.backend.Controller.Rest;

import de.wirvsvirus.gemeinsamsport.backend.Dao.GroupDao;
import de.wirvsvirus.gemeinsamsport.backend.Domain.Group;
import de.wirvsvirus.gemeinsamsport.backend.Dto.GroupInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    @Autowired
    private GroupDao groupDao;

    @GetMapping("/group/{id}")
    public GroupInfo getGroup(@PathVariable("id") long id) {
        return new GroupInfo(id, "http://example.com");
    }

    @PostMapping("/group/")
    public long putGroup(@RequestBody GroupInfo groupInfo) {
        Group group = new Group(null, groupInfo.getUrl());
        groupDao.save(group);
        return group.getId();
    }
}
