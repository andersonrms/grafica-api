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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("product")
public class ProductsController {
    @Autowired
    private ProductRepository repository;

    @PostMapping()
    @Transactional
    public void create(@Valid @RequestBody ProductDTO productDTO) {
        var product = new Product(productDTO);
        repository.save(product);
    }

    @GetMapping()
    public Page<ProductDTO> list(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        return repository.findAllByActiveTrue(pageable).map(ProductDTO::new);
    }

    @PutMapping()
    @Transactional
    public ProductDTO update(@Valid @RequestBody UpdateProductDTO updateProductDTO){
        var product = repository.getReferenceById(updateProductDTO.id());
        product.update(updateProductDTO);

        return new ProductDTO(product);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var product = repository.getReferenceById(id);
        product.delete();
    }
}
