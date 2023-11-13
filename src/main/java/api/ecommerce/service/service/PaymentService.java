//package api.ecommerce.service.service;
//
//import api.ecommerce.service.domains.entity.Client;
//import api.ecommerce.service.domains.entity.Payment;
//import api.ecommerce.service.domains.model.ClientDto;
//import api.ecommerce.service.domains.model.PaymentDto;
//import api.ecommerce.service.domains.repository.ClientRepository;
//import api.ecommerce.service.domains.repository.ItensPaymentRepository;
//import api.ecommerce.service.domains.repository.PaymentRepository;
//import api.ecommerce.service.exception.handler.HandlerEntityNotFoundException;
//import api.ecommerce.service.exception.handler.HandlerError;
//import api.ecommerce.service.rest.request.RequestPayment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class PaymentService {
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    @Autowired
//    private ItensPaymentRepository itensPaymentRepository;
//    @Autowired
//    private ClientRepository clientRepository;
//
//    public PaymentDto createPayment (RequestPayment requestPayment, Long idClient){
//
//        Client client = clientRepository.findById(idClient)
//                .orElseThrow(() -> new HandlerEntityNotFoundException("Client not found com id " + idClient));
//        try {
//            Payment payment = new Payment();
//            payment.setTpPayment(requestPayment.getTpPayment());
//            payment.setDsStatusPayment(requestPayment.getDsStatusPayment());
//            payment.setPayday(LocalDate.now());
//            payment.setClient(client);
//            payment.setPayTotal(requestPayment.getPayTotal());
//            paymentRepository.save(payment);
//
//            ClientDto clientDto = ClientDto.builder()
//                                            .id(client.getId())
//                                            .name(client.getName())
//                                            .email(client.getEmail())
//                                            .cpf(client.getCpf())
//                                            .age(client.getAge())
//                                            .dateOfbirth(client.getDateOfBirth())
//                                            .tel(client.getTel())
//                                            .build();
//
//            return PaymentDto.builder()
//                            .id(payment.getId())
//                            .tpPayment(payment.getTpPayment())
//                            .dsStatusPayment(payment.getDsStatusPayment())
//                            .payday(payment.getPayday())
//                            .payTotal(payment.getPayTotal())
//                            .client(clientDto)
//                            .build();
//        }catch (Exception ex){
//            throw new HandlerError(ex.getMessage());
//        }
//    }
//    public List<PaymentDto> findAllPayment(){
//        List<Payment> payments = paymentRepository.findAll();
//        List<PaymentDto> paymentDtos = new ArrayList<>();
//        try {
//            payments.forEach(payment -> {
//                var client = payment.getClient();
//
//                ClientDto clientDto = ClientDto.builder()
//                                                .id(client.getId())
//                                                .name(client.getName())
//                                                .email(client.getEmail())
//                                                .cpf(client.getCpf())
//                                                .age(client.getAge())
//                                                .dateOfbirth(client.getDateOfBirth())
//                                                .tel(client.getTel())
//                                                .build();
//
//                PaymentDto.builder()
//                        .id(payment.getId())
//                        .tpPayment(payment.getTpPayment())
//                        .dsStatusPayment(payment.getDsStatusPayment())
//                        .payday(payment.getPayday())
//                        .payTotal(payment.getPayTotal())
//                        .client(clientDto)
//                        .build();
//
//                payments.add(payment);
//            });
//            return paymentDtos;
//        }catch (Exception ex){
//            throw new HandlerError(ex.getMessage());
//        }
//    }
//
//    public PaymentDto findByPayment(Long idPayment) {
//        Payment payment = paymentRepository.findById(idPayment)
//                .orElseThrow(() -> new HandlerEntityNotFoundException("Payment not found com id " + idPayment));
//
//        var client = payment.getClient();
//        try {
//
//            ClientDto clientDto = ClientDto.builder()
//                    .id(client.getId())
//                    .name(client.getName())
//                    .email(client.getEmail())
//                    .cpf(client.getCpf())
//                    .age(client.getAge())
//                    .dateOfbirth(client.getDateOfBirth())
//                    .tel(client.getTel())
//                    .build();
//
//            return PaymentDto.builder()
//                    .id(payment.getId())
//                    .tpPayment(payment.getTpPayment())
//                    .dsStatusPayment(payment.getDsStatusPayment())
//                    .payday(payment.getPayday())
//                    .payTotal(payment.getPayTotal())
//                    .client(clientDto)
//                    .build();
//        }catch (Exception ex){
//            throw new HandlerError(ex.getMessage());
//        }
//    }
//    public PaymentDto updatePayment (RequestPayment requestPayment, Long idClient){
//
//        Client client = clientRepository.findById(idClient)
//                .orElseThrow(() -> new HandlerEntityNotFoundException("Client not found com id " + idClient));
//        try {
//            Payment payment = new Payment();
//            payment.setTpPayment(requestPayment.getTpPayment());
//            payment.setDsStatusPayment(requestPayment.getDsStatusPayment());
//            payment.setPayday(LocalDate.now());
//            payment.setClient(client);
//            payment.setPayTotal(requestPayment.getPayTotal());
//            paymentRepository.save(payment);
//
//            return new PaymentDto("Payment update successfully");
//        }catch (Exception ex){
//            throw new HandlerError(ex.getMessage());
//        }
//    }
//
//    public PaymentDto deletePayment(Long idPayment) {
//        Payment payment = paymentRepository.findById(idPayment)
//                .orElseThrow(() -> new HandlerEntityNotFoundException("Payment not found com id " + idPayment));
//        try {
//            paymentRepository.delete(payment);
//            return new PaymentDto("Payment deleted successfully");
//        }catch (Exception ex){
//            throw new HandlerError(ex.getMessage());
//        }
//    }
//}
