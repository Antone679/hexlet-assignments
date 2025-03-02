package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name = "cars")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @NotNull
    private String model;
    @NotNull
    private String manufacturer;
    @NotNull
    private int enginePower;

    @LastModifiedDate
    private LocalDate updatedAt;

    @CreatedDate
    private LocalDate createdAt;
}
