package uz.bob.school_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.school_app.entity.Group;
import uz.bob.school_app.entity.School;
import uz.bob.school_app.entity.Teacher;
import uz.bob.school_app.payload.GroupDto;
import uz.bob.school_app.repository.GroupRepository;
import uz.bob.school_app.repository.SchoolRepository;
import uz.bob.school_app.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SchoolRepository schoolRepository;

    @GetMapping
    public List<Group> get(){
        return groupRepository.findAll();
    }

    @PostMapping
    public String add(@RequestBody GroupDto groupDto){
        Group group=new Group();
        boolean exists = groupRepository.existsByNameAndSchoolId(groupDto.getName(), groupDto.getSchoolId());
        if (exists){
            return "This group such as in the school";
        }
        group.setName(groupDto.getName());
        Optional<School> optionalSchool = schoolRepository.findById(groupDto.getSchoolId());
        if (!optionalSchool.isPresent()) {
            return "School not found";        }
        group.setSchool(optionalSchool.get());
        groupRepository.save(group);
        return "Group saved";
    }
    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id,@RequestBody GroupDto groupDto){
        if (!groupRepository.findById(id).isPresent()) {
            return "Group not edited";
        }
        Group group = groupRepository.getById(id);
        group.setName(groupDto.getName());

        group.setSchool(schoolRepository.getById(groupDto.getSchoolId()));
        return "Group edited";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        if (!groupRepository.findById(id).isPresent()) {
            return "Group not founded";
        }
        groupRepository.deleteById(id);
        return "Group deleted";
    }
}
