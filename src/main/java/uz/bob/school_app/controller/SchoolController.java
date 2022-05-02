package uz.bob.school_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.bob.school_app.entity.School;
import uz.bob.school_app.repository.SchoolRepository;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    // TODO: 5/2/2022 continent,country... add address classes, project last time

    @Autowired
    SchoolRepository schoolRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<School> getSchools(){
        List<School> schoolList = schoolRepository.findAll();
        return schoolList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addSchool(@RequestBody School school){
        schoolRepository.save(school);
        return "School added";
    }

}
