package api.ecommerce.service.service;

import api.ecommerce.service.domains.entity.Product;
import api.ecommerce.service.domains.entity.Store;
import api.ecommerce.service.domains.model.ProductDto;
import api.ecommerce.service.domains.model.StoreDto;
import api.ecommerce.service.domains.repository.ProductRepository;
import api.ecommerce.service.domains.repository.StoreRepository;
import api.ecommerce.service.exception.handler.HandlerEntityNotFoundException;
import api.ecommerce.service.exception.handler.HandlerError;
import api.ecommerce.service.rest.request.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    public ProductDto createProduct (RequestProduct requestProduct,Long idStore){
        Store store = storeRepository.findById(idStore)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Product not found com id " + idStore));
        try {
            Product product = new Product();
            product.setName(requestProduct.getName());
            product.setDescription(requestProduct.getDescriptyon());
            product.setTypeProduct(requestProduct.getTypeProduct());
            product.setPrice(requestProduct.getPrice());
            product.setDiscount(requestProduct.getDiscount());
            product.setQtItemStock(requestProduct.getQtItemStock());
            product.setStore(store);
            productRepository.save(product);

            StoreDto storeDto = StoreDto.builder()
                                        .id(store.getId())
                                        .cnpj(store.getCnpj())
                                        .name(store.getName())
                                        .build();

            return ProductDto.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .descriptyon(product.getDescription())
                            .typeProduct(product.getTypeProduct())
                            .price(itemDiscount(product.getPrice(),product.getDiscount()))
                            .qtItemStock(product.getQtItemStock())
                            .discount(product.getDiscount())
                            .store(storeDto)
                            .build();

        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public List<ProductDto> findAllProduct(){
        try {
            List<Product> products = productRepository.findAll();
            List<ProductDto> productDtos = new ArrayList<>();

            products.forEach(product -> {
                var store = product.getStore();

                StoreDto storeDto = StoreDto.builder()
                                            .id(store.getId())
                                            .cnpj(store.getCnpj())
                                            .name(store.getName())
                                            .build();

                ProductDto productDto = ProductDto.builder()
                                                    .id(product.getId())
                                                    .name(product.getName())
                                                    .descriptyon(product.getDescription())
                                                    .typeProduct(product.getTypeProduct())
                                                    .price(product.getPrice())
                                                    .qtItemStock(product.getQtItemStock())
                                                    .discount(product.getDiscount())
                                                    .store(storeDto)
                                                    .build();

                productDtos.add(productDto);
            });
            return productDtos;
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public ProductDto findByProduct(Long idProduct){
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Product not found com id " + idProduct));
        try {
            var store = product.getStore();

            StoreDto storeDto = StoreDto.builder()
                                        .id(store.getId())
                                        .cnpj(store.getCnpj())
                                        .name(store.getName())
                                        .build();

            return ProductDto.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .descriptyon(product.getDescription())
                            .typeProduct(product.getTypeProduct())
                            .price(product.getPrice())
                            .qtItemStock(product.getQtItemStock())
                            .discount(product.getDiscount())
                            .store(storeDto)
                            .build();
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }

    public ProductDto updateProduct (RequestProduct requestProduct,Long idProduct){
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Product not found com id " + idProduct));
        try {

            product.setName(requestProduct.getName());
            product.setDescription(requestProduct.getDescriptyon());
            product.setTypeProduct(requestProduct.getTypeProduct());
            product.setPrice(requestProduct.getPrice());
            product.setDiscount(requestProduct.getDiscount());
            product.setQtItemStock(requestProduct.getQtItemStock());
            productRepository.save(product);

            var store = product.getStore();

            StoreDto storeDto = StoreDto.builder()
                                        .id(store.getId())
                                        .cnpj(store.getCnpj())
                                        .name(store.getName())
                                        .build();

            return ProductDto.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .descriptyon(product.getDescription())
                            .typeProduct(product.getTypeProduct())
                            .price(product.getPrice())
                            .qtItemStock(product.getQtItemStock())
                            .discount(product.getDiscount())
                            .store(storeDto)
                            .build();
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }

    }
    public String deleteProduct (Long idProduct){

        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Product not found com id " + idProduct));

        try {
            productRepository.delete(product);

            return "successful delete";

        }catch (Exception ex) {
            throw new HandlerError(ex.getMessage());
        }
    }
    public double itemDiscount(double priceOrigin, double valueDiscount){
        if (valueDiscount < 0 || valueDiscount >100){
            throw new IllegalArgumentException("invalid percentage value");
        }
        var discount = priceOrigin*valueDiscount / 100;
        var discountedPrice = priceOrigin - discount;

        return discountedPrice;
    }
}
