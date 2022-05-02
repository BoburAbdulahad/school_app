package uz.bob.school_app.payload;

import lombok.Data;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer groupId;
}
