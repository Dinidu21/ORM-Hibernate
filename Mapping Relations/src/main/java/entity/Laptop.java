package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class Laptop {
    @Id
    private int id;
    private String brand;
    private String model;
    private int year;
    private int price;
}
