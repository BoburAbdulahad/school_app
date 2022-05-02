package uz.bob.school_app.payload;

import lombok.Data;

@Data
public class TimeTableDto {

    private String day;
    private String start;

    private Integer groupId;
    private Integer subjectId;
    private Integer teacherId;

}
