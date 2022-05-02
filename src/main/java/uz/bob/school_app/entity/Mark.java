package uz.bob.school_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Student student;

    @OneToOne
    private Subject subject;

    @OneToOne
    private Teacher teacher;

    @Column(nullable = false)
    private Integer markValue;

}
