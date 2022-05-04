package uz.bob.school_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "groups")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name","school_id"}))
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private School school;

//    @ManyToMany
//    private List<Teacher> teacher;
}
