package api.ecommerce.service.rest.controller;

import api.ecommerce.service.domains.model.ProductDto;
import api.ecommerce.service.rest.request.RequestProduct;
import api.ecommerce.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/store/{idStore}")
    public ResponseEntity<ProductDto> createProduct(@RequestBody RequestProduct requestProduct, @PathVariable Long idStore){
        ProductDto productDto = productService.createProduct(requestProduct, idStore);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAllProduct(){
        List<ProductDto> stores = productService.findAllProduct();
        return ResponseEntity.status(HttpStatus.OK).body(stores);
    }
    @GetMapping(value = "/{idProduct}")
    public ResponseEntity<ProductDto> findByProduct(@PathVariable Long idProduct){
        ProductDto store = productService.findByProduct(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(store);
    }
    @PutMapping(value = "/{idProduct}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody RequestProduct requestProduct, @PathVariable Long idProduct){
        ProductDto storeDto = productService.updateProduct(requestProduct,idProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeDto);
    }
    @DeleteMapping(value = "/{idProduct}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long idProduct){
        String store = productService.deleteProduct(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(store);
    }
}
