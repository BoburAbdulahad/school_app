package uz.bob.school_app.payload;

import lombok.Data;

@Data
public class MarkDto {
    private Integer studentId;
    private Integer subjectId;
    private Integer teacherId;
    private Integer markValue;

}
