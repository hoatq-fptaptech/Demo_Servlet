package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(unique = true)
    public String name;
    public String room;

    @OneToMany(mappedBy = "classes")
    public List<Student> students;
}
