package de.wirvsvirus.gemeinsamsport.backend.Controller.Rest;

import de.wirvsvirus.gemeinsamsport.backend.Dao.GroupDao;
import de.wirvsvirus.gemeinsamsport.backend.Dao.WorkoutStepDao;
import de.wirvsvirus.gemeinsamsport.backend.Dto.WorkoutStepInfo;
import de.wirvsvirus.gemeinsamsport.backend.Entity.Group;
import de.wirvsvirus.gemeinsamsport.backend.Entity.WorkoutStep;
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
public class WorkoutStepController {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private WorkoutStepDao workoutStepDao;

    @GetMapping("/group/{groupId}/workout/steps/")
    public Collection<WorkoutStepInfo> getWorkoutStepsByGroup(@PathVariable("groupId") long groupId) {
        Optional<Group> maybeGroup = groupDao.findById(groupId);
        if (maybeGroup.isPresent()) {
            return maybeGroup.get()
                    .getWorkoutSteps()
                    .stream()
                    .map(workoutStep -> new WorkoutStepInfo(workoutStep.getId(), workoutStep.getGroup().getId(), workoutStep.getDescription(), workoutStep.getDuration()))
                    .collect(Collectors.toList());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist");
    }

    @GetMapping("/group/{groupId}/workout/steps/{workoutStepId}")
    public WorkoutStepInfo getWorkoutStep(@PathVariable("groupId") long groupId, @PathVariable("workoutStepId") long workoutStepId) {
        Optional<WorkoutStep> maybeWorkoutStep = workoutStepDao.findById(workoutStepId);
        if (maybeWorkoutStep.isPresent() && groupId == maybeWorkoutStep.get().getGroup().getId()) {
            WorkoutStep workoutStep = maybeWorkoutStep.get();
            return new WorkoutStepInfo(workoutStep.getId(), workoutStep.getGroup().getId(), workoutStep.getDescription(), workoutStep.getDuration());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This workout step does not exist");
    }

    @PostMapping("/group/{groupId}/workout/steps/")
    public long createWorkoutStep(@PathVariable("groupId") long groupId, @RequestBody WorkoutStepInfo workoutStepInfo) {
        Optional<Group> maybeGroup = groupDao.findById(groupId);
        if (maybeGroup.isPresent()) {
            WorkoutStep workoutStep = new WorkoutStep();
            workoutStep.setGroup(maybeGroup.get());
            workoutStep.setDescription(workoutStepInfo.getDescription());
            workoutStep.setDuration(workoutStepInfo.getDuration());
            workoutStepDao.save(workoutStep);
            return workoutStep.getId();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist");
    }
}
