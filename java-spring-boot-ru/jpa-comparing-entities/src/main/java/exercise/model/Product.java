package exercise.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.*;
import lombok.experimental.FieldDefaults;

// BEGIN
@Entity
@Table
@EqualsAndHashCode(of = {"title", "price"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String title;
    Integer price;
}
// END
