package entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class StudentName {
    private String firstName;
    private String lastName;

}
