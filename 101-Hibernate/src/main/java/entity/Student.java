package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor

@Entity
@Table(name = "student_table")
public class Student {
    @Id
    @Column(name="s_id")
    private int id;
    @Column(name = "s_name")
    private String name;
    private int age;
}
