package entity;

import jakarta.persistence.*;
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
    private StudentName studentName;
    private int age;


}