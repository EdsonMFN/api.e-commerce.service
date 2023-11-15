package api.ecommerce.service.service;

import api.ecommerce.service.domains.entity.*;
import api.ecommerce.service.domains.model.*;
import api.ecommerce.service.domains.repository.*;
import api.ecommerce.service.exception.handler.HandlerEntityNotFoundException;
import api.ecommerce.service.rest.request.RequestOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private DeliveryAddressRepository deliveryAddressRepository;

    public OrderDto createOrder(RequestOrder requestOrder, Long idClient){

        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Client not found com id " + idClient));
        List<ItensPaymentDto> itensPaymentDtos = new ArrayList<>();

        var getDeliveryAddress = requestOrder.getDeliveryAddress();
        var getPayment = requestOrder.getPayment();
        final double[] payTotal = {0};



        Payment payment = new Payment();
        payment.setDsStatusPayment(getPayment.getDsStatusPayment());
        payment.setTpPayment(getPayment.getTpPayment());
        payment.setPayday(getPayment.getPayday());
        payment.setPayTotal(payTotal[0]);
        paymentRepository.save(payment);

        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setAddress(getDeliveryAddress.getAddress());
        deliveryAddress.setNumber(getDeliveryAddress.getNumber());
        deliveryAddress.setState(getDeliveryAddress.getState());
        deliveryAddress.setDistrict(getDeliveryAddress.getDistrict());
        deliveryAddressRepository.save(deliveryAddress);

        Order order = new Order();
        order.setClient(client);
        order.setDeliveryAddress(deliveryAddress);
        order.setPayment(payment);
        order.setDateOrder(LocalDateTime.now());
        orderRepository.save(order);

        requestOrder.getItensPayments()
                .forEach(itensPaymentLambd -> {
                    ItensPayment itensPayment = itensPaymentRepository.findById(itensPaymentLambd.getId())
                            .orElseThrow(() -> new HandlerEntityNotFoundException("ItensPayment not found com id " + itensPaymentLambd.getId()));

                    var product = itensPayment.getProduct();

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
                                    .qtProduct(itensPayment.getQtProduct())
                                    .pricePay(itensPayment.getPricePay())
                                    .build();

                    payTotal[0] += itensPayment.getPricePay();

                    itensPayment.setOrder(order);
                    itensPaymentRepository.save(itensPayment);

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
                        .deliveryAddress(deliveryAddressDto)
                        .itensPayments(itensPaymentDtos)
                        .payment(paymentDto)
                        .build();
    }
    public List<OrderDto> findAllOrder(){
        List<Order> orders = orderRepository.findAll();
        List<ItensPaymentDto> itensPaymentDtos = new ArrayList<>();
        List<OrderDto> orderDtos = new ArrayList<>();

        orders.forEach(order -> {

            var store = order.getClient().getStore();
            var deliveryAddress = order.getDeliveryAddress();
            var client = order.getClient();
            var payment = order.getPayment();

            order.getItensPayments().parallelStream()
                    .forEach(itensPayment -> {
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
                                .pricePay(itensPayment.getPricePay())
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
                                            .build();
            PaymentDto paymentDto = PaymentDto.builder()
                                                .id(payment.getId())
                                                .payday(payment.getPayday())
                                                .tpPayment(payment.getTpPayment())
                                                .dsStatusPayment(payment.getDsStatusPayment())
                                                .payTotal(payment.getPayTotal())
                                                .build();
            OrderDto orderDto = OrderDto.builder()
                                        .id(order.getId())
                                        .dateOrder(order.getDateOrder())
                                        .client(clientDto)
                                        .deliveryAddress(deliveryAddressDto)
                                        .itensPayments(itensPaymentDtos)
                                        .payment(paymentDto)
                                        .build();
            orderDtos.add(orderDto);
        });
        return orderDtos;
    }

    public OrderDto findByOrder(Long idOrder) {
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new HandlerEntityNotFoundException("ItensPayment not found com id " + idOrder));
        List<ItensPaymentDto> itensPaymentDtos = new ArrayList<>();

        var store = order.getClient().getStore();
        var deliveryAddress = order.getDeliveryAddress();
        var client = order.getClient();
        var payment = order.getPayment();

        order.getItensPayments().parallelStream().forEach(itensPayment -> {
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
                            .pricePay(itensPayment.getPricePay())
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
                        .deliveryAddress(deliveryAddressDto)
                        .itensPayments(itensPaymentDtos)
                        .payment(paymentDto)
                        .build();
    }
}


























