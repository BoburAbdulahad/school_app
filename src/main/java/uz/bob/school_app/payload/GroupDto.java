package uz.bob.school_app.payload;

import lombok.Data;

import java.util.List;

@Data
public class GroupDto {
    private String name;
    private Integer schoolId;
//    private List<Integer> teachersIds;
}
