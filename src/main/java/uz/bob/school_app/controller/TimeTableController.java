package uz.bob.school_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import uz.bob.school_app.entity.Group;
import uz.bob.school_app.entity.Subject;
import uz.bob.school_app.entity.Teacher;
import uz.bob.school_app.entity.TimeTable;
import uz.bob.school_app.payload.TimeTableDto;
import uz.bob.school_app.repository.GroupRepository;
import uz.bob.school_app.repository.SubjectRepository;
import uz.bob.school_app.repository.TeacherRepository;
import uz.bob.school_app.repository.TimeTableRepository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timetable")
public class TimeTableController {
    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    public List<TimeTable> get(){
        return timeTableRepository.findAll();
    }

    @PostMapping
    public String add(@RequestBody TimeTableDto timeTableDto){
        TimeTable timeTable=new TimeTable();
        timeTable.setDay(timeTableDto.getDay());
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime localTime=LocalTime.parse(timeTableDto.getStart(),formatter);
        timeTable.setStart(localTime);
        timeTable.setEnd(timeTable.getStart().plusMinutes(45));
        Optional<Group> optionalGroup = groupRepository.findById(timeTableDto.getGroupId());
        if (!optionalGroup.isPresent()) {
            return "Group not found";
        }
        timeTable.setGroup(optionalGroup.get());
        Optional<Subject> optionalSubject = subjectRepository.findById(timeTableDto.getSubjectId());
        if (!optionalSubject.isPresent()) {
            return "Subject not founded";
        }
        timeTable.setSubject(optionalSubject.get());
        Optional<Teacher> optionalTeacher = teacherRepository.findById(timeTableDto.getTeacherId());
        if (!optionalTeacher.isPresent()) {
            return "Teacher not founded";
        }
        timeTable.setTeacher(optionalTeacher.get());

        timeTableRepository.save(timeTable);
        return "Time table successfully added";
    }


//    public static void main(String[] args) {
//        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("H:mm:ss");
//        String time="8:00:00";
//        LocalTime localTime=LocalTime.parse(time,formatter);
//        System.out.println(localTime);
//    }
}
