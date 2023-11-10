package api.ecommerce.service.rest.controller;

import api.ecommerce.service.domains.model.PaymentDto;
import api.ecommerce.service.rest.request.RequestPayment;
import api.ecommerce.service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/itensPayment/{idItensPayment}/client/{idClient}")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody RequestPayment requestPayment, @PathVariable Long idItensPayment,@PathVariable Long idClient){
        PaymentDto paymentDto = paymentService.createPayment(requestPayment, idItensPayment,idClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDto);
    }
    @GetMapping
    public ResponseEntity<List<PaymentDto>> findAllPayment(){
        List<PaymentDto> paymentDto = paymentService.findAllPayment();
        return ResponseEntity.status(HttpStatus.OK).body(paymentDto);
    }
    @GetMapping("/{idPayment}")
    public ResponseEntity<PaymentDto> findByPayment(@PathVariable Long idPayment){
        PaymentDto paymentDto = paymentService.findByPayment(idPayment);
        return ResponseEntity.status(HttpStatus.OK).body(paymentDto);
    }
    @PutMapping(value = "/itensPayment/{idItensPayment}/client/{idClient}")
    public ResponseEntity<PaymentDto> updatePayment(@RequestBody RequestPayment requestPayment, @PathVariable Long idItensPayment,@PathVariable Long idClient){
        PaymentDto paymentDto = paymentService.createPayment(requestPayment, idItensPayment,idClient);
        return ResponseEntity.status(HttpStatus.OK).body(paymentDto);
    }
    @DeleteMapping("/{idPayment}")
    public ResponseEntity<PaymentDto> deletePayment(@PathVariable Long idPayment){
        PaymentDto paymentDto = paymentService.deletePayment(idPayment);
        return ResponseEntity.status(HttpStatus.OK).body(paymentDto);
    }
}