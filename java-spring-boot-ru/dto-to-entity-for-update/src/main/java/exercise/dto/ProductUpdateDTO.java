package exercise.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

// BEGIN
@Getter
@Setter
public class ProductUpdateDTO {
    private String title;
    private int price;

}
// END
