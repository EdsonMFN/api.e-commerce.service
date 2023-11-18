package api.ecommerce.service.service;

import api.ecommerce.service.domains.entity.*;
import api.ecommerce.service.domains.model.*;
import api.ecommerce.service.domains.repository.*;
import api.ecommerce.service.exception.handler.HandlerEntityNotFoundException;
import api.ecommerce.service.exception.handler.HandlerError;
import api.ecommerce.service.rest.request.RequestOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ItensPaymentRepository itensPaymentRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderDto createOrder(RequestOrder requestOrder, Long idClient){

        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Client not found com id " + idClient));

        List<ItensPaymentDto> itensPaymentDtos = new ArrayList<>();

        var getPayment = requestOrder.getPayment();
        AtomicReference<Double> payTotal = new AtomicReference<>(0.0);

        Payment payment = new Payment();
        payment.setDsStatusPayment(getPayment.getDsStatusPayment());
        payment.setTpPayment(getPayment.getTpPayment());
        payment.setPayday(getPayment.getPayday());
        payment.setPayTotal(payTotal.get());
        payment = paymentRepository.save(payment);

        Order order = new Order();
        order.setClient(client);
        order.setDateOrder(LocalDateTime.now());
        Order finalOrder = orderRepository.save(order);

        requestOrder.getItensPayments()
                .forEach(itensPaymentLambd -> {
                    Product product = productRepository.findById(itensPaymentLambd.getProduct().getId())
                        .orElseThrow(() -> new HandlerEntityNotFoundException("Product not found with id " + itensPaymentLambd.getProduct().getId()));

                    ItensPayment itensPayment = new ItensPayment();
                    itensPayment.setQtProduct(itensPaymentLambd.getQtProduct());
                    itensPayment.setProduct(product);
                    itensPayment.setSubtotal(pricePay(itensPayment.getQtProduct(), product.getPrice()));
                    itensPayment.setOrder(finalOrder);
                    itensPaymentRepository.save(itensPayment);

                    ProductDto productDto = ProductDto.builder()
                            .name(product.getName())
                            .typeProduct(product.getTypeProduct())
                            .price(product.getPrice())
                            .discount(product.getDiscount())
                            .build();
                    ItensPaymentDto itensPaymentDto =
                            ItensPaymentDto.builder()
                                    .id(itensPayment.getId())
                                    .product(productDto)
                                    .qtProduct(productWithdrawal(itensPayment.getQtProduct(),product.getQtItemStock(),product))
                                    .subtotal(itensPayment.getSubtotal())
                                    .build();

                    payTotal.updateAndGet(v -> v + itensPayment.getSubtotal());

                    itensPaymentDtos.add(itensPaymentDto);
                });

        payment.setPayTotal(payTotal.get());
        paymentRepository.save(payment);

        finalOrder.setPayment(payment);
        orderRepository.save(finalOrder);

        var deliveryAddress = client.getDeliveryAddress();
        DeliveryAddressDto deliveryAddressDto =
                DeliveryAddressDto.builder()
                        .id(deliveryAddress.getId())
                        .address(deliveryAddress.getAddress())
                        .state(deliveryAddress.getState())
                        .number(deliveryAddress.getNumber())
                        .district(deliveryAddress.getDistrict())
                        .build();

        var store = client.getStore();
        StoreDto storeDto = StoreDto.builder()
                                    .id(store.getId())
                                    .cnpj(store.getCnpj())
                                    .name(store.getName())
                                    .build();
        ClientDto clientDto = ClientDto.builder()
                                        .id(client.getId())
                                        .name(client.getName())
                                        .email(client.getEmail())
                                        .cpf(client.getCpf())
                                        .age(client.getAge())
                                        .dateOfbirth(client.getDateOfBirth())
                                        .tel(client.getTel())
                                        .deliveryAddress(deliveryAddressDto)
                                        .storeDto(storeDto)
                                        .build();
        PaymentDto paymentDto = PaymentDto.builder()
                                            .id(payment.getId())
                                            .payday(payment.getPayday())
                                            .tpPayment(payment.getTpPayment())
                                            .dsStatusPayment(payment.getDsStatusPayment())
                                            .payTotal(payment.getPayTotal())
                                            .build();
        return OrderDto.builder()
                        .id(order.getId())
                        .dateOrder(order.getDateOrder())
                        .client(clientDto)
                        .itensPayments(itensPaymentDtos)
                        .payment(paymentDto)
                        .build();
    }
    public List<OrderDto> findAllOrder(){
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();

        orders.forEach(order -> {
            List<ItensPaymentDto> itensPaymentDtos = new ArrayList<>();

            var store = order.getClient().getStore();
            var deliveryAddress = order.getClient().getDeliveryAddress();
            var client = order.getClient();
            var payment = order.getPayment();

            DeliveryAddressDto deliveryAddressDto =
                    DeliveryAddressDto.builder()
                                        .id(deliveryAddress.getId())
                                        .address(deliveryAddress.getAddress())
                                        .state(deliveryAddress.getState())
                                        .number(deliveryAddress.getNumber())
                                        .district(deliveryAddress.getDistrict())
                                        .build();
            StoreDto storeDto = StoreDto.builder()
                                        .id(store.getId())
                                        .cnpj(store.getCnpj())
                                        .name(store.getName())
                                        .build();
            ClientDto clientDto = ClientDto.builder()
                                            .id(client.getId())
                                            .name(client.getName())
                                            .email(client.getEmail())
                                            .cpf(client.getCpf())
                                            .age(client.getAge())
                                            .dateOfbirth(client.getDateOfBirth())
                                            .tel(client.getTel())
                                            .deliveryAddress(deliveryAddressDto)
                                            .storeDto(storeDto)
                                            .build();
            PaymentDto paymentDto = PaymentDto.builder()
                                                .id(payment.getId())
                                                .payday(payment.getPayday())
                                                .tpPayment(payment.getTpPayment())
                                                .dsStatusPayment(payment.getDsStatusPayment())
                                                .payTotal(payment.getPayTotal())
                                                .build();

                order.getItensPayments().forEach(itensPayment -> {
                    var product = itensPayment.getProduct();
                    if (product != null){
                        ProductDto productDto = ProductDto.builder()
                                                            .id(product.getId())
                                                            .name(product.getName())
                                                            .typeProduct(product.getTypeProduct())
                                                            .price(product.getPrice())
                                                            .discount(product.getDiscount())
                                                            .build();
                        ItensPaymentDto itensPaymentDto =
                                ItensPaymentDto.builder()
                                                .id(itensPayment.getId())
                                                .product(productDto)
                                                .qtProduct(itensPayment.getQtProduct())
                                                .subtotal(itensPayment.getSubtotal())
                                                .build();
                        itensPaymentDtos.add(itensPaymentDto);
                    }else {
                     throw new HandlerError("Product not found");
                    }
                });
            OrderDto orderDto = OrderDto.builder()
                                        .id(order.getId())
                                        .dateOrder(order.getDateOrder())
                                        .client(clientDto)
                                        .itensPayments(itensPaymentDtos)
                                        .payment(paymentDto)
                                        .build();
            orderDtos.add(orderDto);
        });
        return orderDtos;
    }

    public OrderDto findByOrder(Long idOrder) {
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Order not found with id " + idOrder));
        List<ItensPaymentDto> itensPaymentDtos = new ArrayList<>();

        var store = order.getClient().getStore();
        var deliveryAddress = order.getClient().getDeliveryAddress();
        var client = order.getClient();
        var payment = order.getPayment();

        order.getItensPayments().forEach(itensPayment -> {
            var product = itensPayment.getProduct();
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .typeProduct(product.getTypeProduct())
                    .price(product.getPrice())
                    .discount(product.getDiscount())
                    .build();

            ItensPaymentDto itensPaymentDto =
                    ItensPaymentDto.builder()
                            .id(itensPayment.getId())
                            .product(productDto)
                            .qtProduct(itensPayment.getQtProduct())
                            .subtotal(itensPayment.getSubtotal())
                            .build();

            itensPaymentDtos.add(itensPaymentDto);
        });
        DeliveryAddressDto deliveryAddressDto =
                DeliveryAddressDto.builder()
                                    .id(deliveryAddress.getId())
                                    .address(deliveryAddress.getAddress())
                                    .state(deliveryAddress.getState())
                                    .number(deliveryAddress.getNumber())
                                    .district(deliveryAddress.getDistrict())
                                    .build();
        StoreDto storeDto = StoreDto.builder()
                                    .id(store.getId())
                                    .cnpj(store.getCnpj())
                                    .name(store.getName())
                                    .build();
        ClientDto clientDto = ClientDto.builder()
                                        .id(client.getId())
                                        .name(client.getName())
                                        .email(client.getEmail())
                                        .cpf(client.getCpf())
                                        .age(client.getAge())
                                        .dateOfbirth(client.getDateOfBirth())
                                        .tel(client.getTel())
                                        .storeDto(storeDto)
                                        .deliveryAddress(deliveryAddressDto)
                                        .build();
        PaymentDto paymentDto = PaymentDto.builder()
                                            .id(payment.getId())
                                            .payday(payment.getPayday())
                                            .tpPayment(payment.getTpPayment())
                                            .dsStatusPayment(payment.getDsStatusPayment())
                                            .payTotal(payment.getPayTotal())
                                            .build();
        return OrderDto.builder()
                        .id(order.getId())
                        .dateOrder(order.getDateOrder())
                        .client(clientDto)
                        .itensPayments(itensPaymentDtos)
                        .payment(paymentDto)
                        .build();
    }
    public OrderDto deleteOrder(Long idOrder){
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Order not found with id " + idOrder));
        try {
            orderRepository.delete(order);

            return new OrderDto("Delete success");

        }catch (Exception ex){
            throw new HandlerError("Unable to delete");
        }
    }
    private Integer productWithdrawal(Integer qtProduct,Integer stock,Product product){

        if (stock >= qtProduct){
            int updateStock = stock - qtProduct;


            product.setQtItemStock(updateStock);
            productRepository.save(product);

            return qtProduct;
        }else {
            throw new HandlerError("There are not enough products in stock");
        }
    }
    private double pricePay(Integer qtProdut, double price){
        return price * qtProdut;
    }
}


























