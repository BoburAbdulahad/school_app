package uz.bob.school_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.school_app.entity.Subject;
import uz.bob.school_app.entity.Teacher;
import uz.bob.school_app.payload.TeacherDto;
import uz.bob.school_app.repository.SubjectRepository;
import uz.bob.school_app.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    List<Subject> subjectList=new ArrayList<>();

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public List<Teacher> get(){
        return teacherRepository.findAll();
    }

    @PostMapping
    public String add(@RequestBody TeacherDto teacherDto){
        Teacher teacher=new Teacher();
        teacher.setFullName(teacherDto.getName());
        boolean existsByPhoneNumber = teacherRepository.existsByPhoneNumber(teacherDto.getPhoneNumber());
        if (existsByPhoneNumber){
            subjectList.clear();
            return "Phone number already exist";
        }
        teacher.setPhoneNumber(teacherDto.getPhoneNumber());
        List<Integer> subjectsIds = teacherDto.getSubjectsIds();
        for (Integer subjectsId : subjectsIds) {
            Optional<Subject> optionalSubject = subjectRepository.findById(subjectsId);
            if (!optionalSubject.isPresent()) {
                subjectList.clear();
                return "Subject not founded";
            }
            subjectList.add(optionalSubject.get());

        }
        teacher.setSubjects(subjectList);
        teacherRepository.save(teacher);
        subjectList.clear();
        return "Teacher saved";
    }
}
