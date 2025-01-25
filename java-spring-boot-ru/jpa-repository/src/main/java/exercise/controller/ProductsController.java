package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Product> index(@RequestParam(required = false) Integer min,
                               @RequestParam(required = false) Integer max){

        min = Optional.ofNullable(min).orElse(0);
        max = Optional.ofNullable(max).orElse(Integer.MAX_VALUE);

        return productRepository.findByPriceBetweenOrderByPriceAsc(min, max);
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }
}
