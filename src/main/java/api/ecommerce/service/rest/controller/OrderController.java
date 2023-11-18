package api.ecommerce.service.rest.controller;

import api.ecommerce.service.domains.model.OrderDto;
import api.ecommerce.service.rest.request.RequestOrder;
import api.ecommerce.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/client/{idClient}")
    public ResponseEntity<OrderDto> createClient(@RequestBody RequestOrder requestOrder,
                                                 @PathVariable Long idClient){
        OrderDto response = orderService.createOrder(requestOrder,idClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<OrderDto>> findAllOrder(){
        List<OrderDto> response = orderService.findAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping(value = "/{idOrder}")
    public ResponseEntity<OrderDto> findByOrder(@PathVariable Long idOrder){
        OrderDto response = orderService.findByOrder(idOrder);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
//    @PutMapping(value = "/{idClient}")
//    public ResponseEntity<OrderDto> updateProduct(@RequestBody RequestClient requestClient, @PathVariable Long idClient){
//        OrderDto response = orderService.(requestClient,idClient);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
    @DeleteMapping(value = "/{idOrder}")
    public ResponseEntity<OrderDto> deleteProduct(@PathVariable Long idOrder){
        OrderDto response = orderService.deleteOrder(idOrder);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
