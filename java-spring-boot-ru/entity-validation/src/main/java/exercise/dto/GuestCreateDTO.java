package exercise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {
    @NotNull
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "^\\+\\d{11,13}$")
    private String phoneNumber;
    @Pattern(regexp = "^\\d{4}$")
    private String clubCard;
    @Future
    private LocalDate cardValidUntil;

}
// END
