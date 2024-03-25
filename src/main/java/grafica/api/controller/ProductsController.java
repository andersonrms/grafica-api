package grafica.api.controller;

import grafica.api.domain.Product.ProductDTO;
import grafica.api.domain.Product.UpdateProductDTO;
import grafica.api.domain.Product.Product;
import grafica.api.domain.Product.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("product")
public class ProductsController {
    @Autowired
    private ProductRepository repository;

    @PostMapping()
    @Transactional
    public ResponseEntity create(@Valid @RequestBody ProductDTO productDTO, UriComponentsBuilder builder) {
        var product = new Product(productDTO);
        repository.save(product);

        var uri = builder.path("/product/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProductDTO(product));
    }

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> list(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        var page = repository.findAllByActiveTrue(pageable).map(ProductDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity list(@PathVariable Long id){
        var product = repository.getReferenceById(id);

        return ResponseEntity.ok(new ProductDTO(product));
    }


    @PutMapping()
    @Transactional
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody UpdateProductDTO updateProductDTO){
        var product = repository.getReferenceById(updateProductDTO.id());
        product.update(updateProductDTO);

        return ResponseEntity.ok(new ProductDTO(product));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var product = repository.getReferenceById(id);
        product.delete();

        return ResponseEntity.noContent().build();
    }
}
