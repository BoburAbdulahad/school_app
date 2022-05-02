package uz.bob.school_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.school_app.entity.Group;
import uz.bob.school_app.entity.Student;
import uz.bob.school_app.payload.StudentDto;
import uz.bob.school_app.repository.GroupRepository;
import uz.bob.school_app.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository groupRepository;

//    @Autowired

    @GetMapping
    public List<Student> get(){
        return studentRepository.findAll();
    }

    @PostMapping
    public String add(@RequestBody StudentDto studentDto){
        Student student=new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()) {
            return "Group not found";
        }
        student.setGroup(optionalGroup.get());
        studentRepository.save(student);
        return "Student saved";
    }
}
