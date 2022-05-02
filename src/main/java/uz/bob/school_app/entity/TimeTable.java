package uz.bob.school_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String day;

    @Column(name = "startTime",nullable = false)
    private LocalTime start;

    @Column(name = "endTime",nullable = false)
    private LocalTime end;

    @OneToOne
    private Group group;

    @OneToOne
    private Subject subject;

    @OneToOne
    private Teacher teacher;

}
