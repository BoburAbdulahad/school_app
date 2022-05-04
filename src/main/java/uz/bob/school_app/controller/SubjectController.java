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

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id,@RequestBody Subject subject){
        if (!subjectRepository.findById(id).isPresent()) {
            return "Subject not edited";
        }
        Subject subject1 = subjectRepository.getById(id);
        subject1.setName(subject.getName());
        return "Subject edited";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        if (!subjectRepository.findById(id).isPresent()) {
            return "Subject not deleted";
        }
        subjectRepository.deleteById(id);
        return "Subject deleted";
    }
}
