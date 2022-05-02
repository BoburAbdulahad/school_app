package uz.bob.school_app.payload;

import lombok.Data;

import java.util.List;

@Data
public class TeacherDto {

    private String name;

    private String phoneNumber;

    private List<Integer> subjectsIds;
}
