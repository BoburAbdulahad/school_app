package uz.bob.school_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.school_app.entity.School;
import uz.bob.school_app.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;

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
    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id,@RequestBody School school){
        Optional<School> optionalSchool = schoolRepository.findById(id);
        if (!optionalSchool.isPresent()) {
            return "School not founded";
        }
        School school1 = optionalSchool.get();
        school1.setName(school.getName());
        schoolRepository.save(school1);
        return "School edited";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        if (!schoolRepository.findById(id).isPresent()) {
            return "School not deleted";
        }
        schoolRepository.deleteById(id);
        return "School deleted";
    }

}
