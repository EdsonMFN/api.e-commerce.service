package api.ecommerce.service.rest.controller;

import api.ecommerce.service.domains.model.ItensPaymentDto;
import api.ecommerce.service.rest.request.RequestItensPayment;
import api.ecommerce.service.service.ItensPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itensPayment")
public class ItensPaymentController {

    @Autowired
    private ItensPaymentService itensPaymentService;

    @PostMapping(value = "/product/{idProduct}")
    public ResponseEntity<ItensPaymentDto> createItensPayment(@RequestBody RequestItensPayment requestItensPayment, @PathVariable Long idProduct){
        ItensPaymentDto itensPaymentDto = itensPaymentService.createBuy(requestItensPayment,idProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(itensPaymentDto);
    }
    @GetMapping
    public ResponseEntity<List<ItensPaymentDto>> findAllItensPayment(){
        List<ItensPaymentDto> itensPaymentDtos = itensPaymentService.findAllBuy();
        return ResponseEntity.status(HttpStatus.OK).body(itensPaymentDtos);
    }
    @GetMapping(value = "/{idItensPayment}")
    public ResponseEntity<ItensPaymentDto> findByItensPayment(@PathVariable Long idItensPayment){
        ItensPaymentDto itensPaymentDto = itensPaymentService.findByBuy(idItensPayment);
        return ResponseEntity.status(HttpStatus.OK).body(itensPaymentDto);
    }
    @PutMapping(value = "/{idItensPayment}/product/{idproduct}")
    public ResponseEntity<ItensPaymentDto> updateItensPayment(@RequestBody RequestItensPayment requestItensPayment, @PathVariable Long idItensPayment,@PathVariable Long idProduct){
        ItensPaymentDto itensPaymentDto = itensPaymentService.updateBuy(requestItensPayment,idItensPayment,idProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(itensPaymentDto);
    }
    @DeleteMapping(value = "/{idItensPayment}")
    public ResponseEntity<String> deleteItensPayment(@PathVariable Long idItensPayment){
        String buy = itensPaymentService.deleteBuy(idItensPayment);
        return ResponseEntity.status(HttpStatus.OK).body(buy);
    }
}
