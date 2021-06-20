package pl.tl.crm.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(value = "/products")
    ResponseEntity getAllProducts() throws JsonProcessingException {
        List<Product> allProducts = productRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(allProducts));
    }

    @GetMapping(value = "/products/{id}")
    ResponseEntity getProductById(@PathVariable("id") Integer id) throws JsonProcessingException {
        Optional<Product> productById = productRepository.findById(id);
        if (productById.isPresent()) {
            return ResponseEntity.ok(objectMapper.writeValueAsString(productById));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/products", params = "name")
    public ResponseEntity getProductsByNameContaining(@Param("name") String name) throws JsonProcessingException {
        List<Product> products = productRepository.findByNameContaining(name);

        return ResponseEntity.ok(objectMapper.writeValueAsString(products));
    }

    @GetMapping(value = "/products", params = "description")
    public ResponseEntity getProductsByDescriptionContaining(@Param("description") String description) throws JsonProcessingException {
        List<Product> products = productRepository.findByDescriptionContaining(description);

        return ResponseEntity.ok(objectMapper.writeValueAsString(products));
    }

    @GetMapping(value = "/products", params = "maxPrice")
    ResponseEntity getProductsCheaperThan(@Param("maxPrice") Integer maxPrice) throws JsonProcessingException {
        List<Product> productsCheaperThanMax = productRepository.findAll().stream()
                .filter(product -> (product.getPrice() < maxPrice))
                .collect(Collectors.toList());

        return ResponseEntity.ok(objectMapper.writeValueAsString(productsCheaperThanMax));
    }

    @GetMapping(value = "/products", params = "minPrice")
    ResponseEntity getProductsMoreExpensiveThan(@Param("minPrice") Integer minPrice) throws JsonProcessingException {
        List<Product> productsMoreExpensiveThanMin = productRepository.findAll().stream()
                .filter(product -> (product.getPrice() > minPrice))
                .collect(Collectors.toList());

        return ResponseEntity.ok(objectMapper.writeValueAsString(productsMoreExpensiveThanMin));
    }

    @GetMapping(value = "/products", params = {"minPrice", "maxPrice"})
    ResponseEntity getProductsWithPriceBetween(@Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice) throws JsonProcessingException {
        List<Product> productsWithPriceBetweenMinAndMax = productRepository.findByPriceBetween(minPrice, maxPrice);

        return ResponseEntity.ok(objectMapper.writeValueAsString(productsWithPriceBetweenMinAndMax));
    }

    @PostMapping(value = "/products")
    public ResponseEntity addProduct(@RequestBody Product newProduct) throws JsonProcessingException {

        Optional<Product> productFromDb = productRepository.findByName(newProduct.getName());
        if (productFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Product savedProduct = productRepository.save(newProduct);
        return ResponseEntity.ok(objectMapper.writeValueAsString(savedProduct));
    }

    @PutMapping(value = "/products/{id}", params = "newPrice")
    ResponseEntity setProductPrice(@PathVariable("id") Integer id, @Param("newPrice") Integer newPrice) {
        if (newPrice <= 0) {
            return ResponseEntity.badRequest().build();
        }
        if (productRepository.existsById(id)) {

            Product productFromRepository = productRepository.findById(id).get();
            productFromRepository.setPrice(newPrice);
            productRepository.save(productFromRepository);
            return ResponseEntity.ok(productFromRepository);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/products/{id}")
    ResponseEntity deleteProductById(@PathVariable("id") Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
