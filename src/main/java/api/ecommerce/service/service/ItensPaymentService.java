package api.ecommerce.service.service;

import api.ecommerce.service.domains.entity.ItensPayment;
import api.ecommerce.service.domains.entity.Product;
import api.ecommerce.service.domains.model.ItensPaymentDto;
import api.ecommerce.service.domains.model.ProductDto;
import api.ecommerce.service.domains.model.StoreDto;
import api.ecommerce.service.domains.repository.ClientRepository;
import api.ecommerce.service.domains.repository.ItensPaymentRepository;
import api.ecommerce.service.domains.repository.PaymentRepository;
import api.ecommerce.service.domains.repository.ProductRepository;
import api.ecommerce.service.exception.handler.HandlerEntityNotFoundException;
import api.ecommerce.service.exception.handler.HandlerError;
import api.ecommerce.service.rest.request.RequestItensPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItensPaymentService {

    @Autowired
    private ItensPaymentRepository itensPaymentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public ItensPaymentDto createBuy (RequestItensPayment requestItensPayment,Long idProduct){
            Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Product not found com id " + idProduct));
        try {
            ItensPayment itensPayment = new ItensPayment();
            itensPayment.setPricePay(requestItensPayment.getPricePay());
            itensPayment.setQtProduct(requestItensPayment.getQtProduct());
            itensPayment.setProduct(product);
            itensPaymentRepository.save(itensPayment);

            var store = product.getStore();

            StoreDto storeDto = StoreDto
                    .builder()
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

            return ItensPaymentDto.builder()
                    .id(itensPayment.getId())
                    .product(productDto)
                    .qtProduct(itensPayment.getQtProduct())
                    .pricePay(itensPayment.getPricePay())
                    .build();

        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public List<ItensPaymentDto> findAllBuy(){
        try {
            List<ItensPayment> itensPayments = itensPaymentRepository.findAll();
            List<ItensPaymentDto> itensPaymentDtos = new ArrayList<>();
            List<ProductDto> productsDto = new ArrayList<>();

            itensPayments.forEach(itensPayment -> {
                var product = itensPayment.getProduct();
                var store = itensPayment.getProduct().getStore();

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

                ItensPaymentDto itensPaymentDto = ItensPaymentDto.builder()
                                        .id(itensPayment.getId())
                                        .qtProduct(itensPayment.getQtProduct())
                                        .product(productDto)
                                        .pricePay(itensPayment.getPricePay())
                                        .build();


                itensPaymentDtos.add(itensPaymentDto);
            });

            return itensPaymentDtos;
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public ItensPaymentDto findByBuy(Long idItensPayment){

        ItensPayment itensPayment = itensPaymentRepository.findById(idItensPayment)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Itens Payment not found com id " + idItensPayment));
        try {
            var product = itensPayment.getProduct();
            var store = itensPayment.getProduct().getStore();

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

            return  ItensPaymentDto.builder()
                                    .id(itensPayment.getId())
                                    .qtProduct(itensPayment.getQtProduct())
                                    .product(productDto)
                                    .pricePay(itensPayment.getPricePay())
                                    .build();
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }

    public ItensPaymentDto updateBuy (RequestItensPayment requestItensPayment, Long idItensPayment, Long idProduct){
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Product not found com id " + idProduct));

        ItensPayment itensPayment = itensPaymentRepository.findById(idItensPayment)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Itens Payment not found com id " + idItensPayment));
        try {

            itensPayment.setPricePay(requestItensPayment.getPricePay());
            itensPayment.setQtProduct(requestItensPayment.getQtProduct());
            itensPayment.setProduct(product);
            itensPaymentRepository.save(itensPayment);

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

            return ItensPaymentDto.builder()
                                    .id(itensPayment.getId())
                                    .product(productDto)
                                    .qtProduct(itensPayment.getQtProduct())
                                    .pricePay(itensPayment.getPricePay())
                                    .build();
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }

    }
    public String deleteBuy (Long idItensPayment){

        ItensPayment itensPayment = itensPaymentRepository.findById(idItensPayment)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Itens Payment not found com id " + idItensPayment));

        try {
            itensPaymentRepository.delete(itensPayment);

            return "successful delete";

        }catch (Exception ex) {
            throw new HandlerError(ex.getMessage());
        }
    }

}
