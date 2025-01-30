package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN

    @GetMapping
    public List<ProductDTO> index() {
        return productRepository.findAll()
                .stream()
                .map(product -> productMapper.map(product))
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No product with id: " + id));

        return productMapper.map(product);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody @Valid ProductCreateDTO productCreateDTO) {
        Product product = productMapper.map(productCreateDTO);
        productRepository.save(product);

        return productMapper.map(product);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody @Valid ProductUpdateDTO productUpdateDTO,
                             @PathVariable long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No product with id: " + id));

        productMapper.update(productUpdateDTO, product);
        productRepository.save(product);

        return productMapper.map(product);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
    productRepository.deleteById(id);
    }

    //GET /products – просмотр списка всех товаров
    //GET /products/{id} – просмотр конкретного товара
    //POST /products – добавление нового товара. При указании id несуществующей категории должен вернуться ответ с кодом 400 Bad request
    //PUT /products/{id} – редактирование товара. При редактировании мы должны иметь возможность поменять название, цену и категорию товара. При указании id несуществующей категории также должен вернуться ответ с кодом 400 Bad request
    //DELETE /products/{id} – удаление товара
    // END
}
