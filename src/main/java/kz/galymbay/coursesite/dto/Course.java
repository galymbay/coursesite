package kz.galymbay.coursesite.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "courses")
    private Set<User> users;

    @ManyToMany(mappedBy = "courses")
    private Set<Mentor> mentors;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
