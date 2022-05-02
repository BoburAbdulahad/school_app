package uz.bob.school_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.school_app.entity.Subject;
import uz.bob.school_app.repository.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public List<Subject> get(){
        return subjectRepository.findAll();
    }

    @PostMapping
    public String add(@RequestBody Subject subject){
        subjectRepository.save(subject);
        return "Subject added";
    }

}
