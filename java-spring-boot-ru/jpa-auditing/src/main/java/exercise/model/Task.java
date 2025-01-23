package exercise.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

// BEGIN
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(value = AuditingEntityListener.class)
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String title;
    String description;
    @CreatedDate
    LocalDate createdAt;
    @LastModifiedDate
    LocalDate updatedAt;
}
// END
