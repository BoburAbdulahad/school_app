package uz.bob.school_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.school_app.entity.Mark;
import uz.bob.school_app.entity.Student;
import uz.bob.school_app.entity.Subject;
import uz.bob.school_app.entity.Teacher;
import uz.bob.school_app.payload.MarkDto;
import uz.bob.school_app.repository.MarkRepository;
import uz.bob.school_app.repository.StudentRepository;
import uz.bob.school_app.repository.SubjectRepository;
import uz.bob.school_app.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mark")
public class MarkController {
    @Autowired
    MarkRepository markRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    public List<Mark> get() {
        return markRepository.findAll();
    }

    @PostMapping
    public String add(@RequestBody MarkDto markDto) {
        Mark mark = new Mark();
        Optional<Student> optionalStudent = studentRepository.findById(markDto.getStudentId());
        if (!optionalStudent.isPresent()) {
            return "Student not founded";
        }
        mark.setStudent(optionalStudent.get());
        Optional<Subject> optionalSubject = subjectRepository.findById(markDto.getSubjectId());
        if (!optionalSubject.isPresent()) {
            return "Subject not founded";
        }
        mark.setSubject(optionalSubject.get());
        Optional<Teacher> optionalTeacher = teacherRepository.findById(markDto.getTeacherId());
        if (!optionalTeacher.isPresent()) {
            return "Teacher not founded";
        }
        mark.setTeacher(optionalTeacher.get());
        mark.setMarkValue(markDto.getMarkValue());

        markRepository.save(mark);
        return "Mark saved";
    }
    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id,@RequestBody MarkDto markDto){
        if (!markRepository.findById(id).isPresent()) {
            return "Mark row not found by id";
        }
        Mark mark = markRepository.getById(id);
        mark.setStudent(studentRepository.getById(markDto.getStudentId()));
        mark.setSubject(subjectRepository.getById(markDto.getSubjectId()));
        mark.setTeacher(teacherRepository.getById(markDto.getTeacherId()));
        mark.setMarkValue(markDto.getMarkValue());
        markRepository.save(mark);
        return "Mark edited";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        Optional<Mark> optionalMark = markRepository.findById(id);
        if (!optionalMark.isPresent()) {
            return "Mark not founded";
        }
        markRepository.delete(optionalMark.get());
        return "Mark deleted";
    }
}
