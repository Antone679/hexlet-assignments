package exercise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// BEGIN
@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private long id;
    private String body;
}
// END
