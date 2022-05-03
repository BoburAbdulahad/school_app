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

    @Autowired
    TeacherRepository teacherRepository;

    List<Teacher> teacherList=new ArrayList<>();

    @GetMapping
    public List<Group> get(){
        return groupRepository.findAll();
    }

    @PostMapping
    public String add(@RequestBody GroupDto groupDto){
        Group group=new Group();
        boolean exists = groupRepository.existsByNameAndSchoolId(groupDto.getName(), groupDto.getSchoolId());
        if (exists){
            teacherList.clear();
            return "This group such as in the school";
        }

        group.setName(groupDto.getName());

        Optional<School> optionalSchool = schoolRepository.findById(groupDto.getSchoolId());

        if (!optionalSchool.isPresent()) {
            teacherList.clear();
            return "School not found";
        }
        group.setSchool(optionalSchool.get());

        groupDto.getTeachersIds().forEach(integer -> teacherRepository.findById(integer).ifPresent(teacherList::add));
        if (teacherList.isEmpty()) {
            teacherList.clear();
            return "Teachers not found";
        }
        group.setTeacher(teacherList);
        groupRepository.save(group);
        teacherList.clear();
        return "Group saved";
    }
}
