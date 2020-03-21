package de.wirvsvirus.gemeinsamsport.backend.Controller.Rest;

import de.wirvsvirus.gemeinsamsport.backend.Dto.GroupInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    @GetMapping("/{id}")
    public GroupInfo getGroup(@PathVariable("id") long id) {
        return new GroupInfo(id, "http://example.com");
    }
}
