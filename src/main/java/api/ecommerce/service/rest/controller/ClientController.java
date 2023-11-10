package api.ecommerce.service.rest.controller;

import api.ecommerce.service.domains.model.ClientDto;
import api.ecommerce.service.rest.request.RequestClient;
import api.ecommerce.service.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/store/{idStore}")
    public ResponseEntity<ClientDto> createClient(@RequestBody RequestClient requestClient, @PathVariable Long idStore){
        ClientDto clientDto = clientService.createClient(requestClient,idStore);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDto);
    }
    @GetMapping
    public ResponseEntity<List<ClientDto>> findAllProduct(){
        List<ClientDto> stores = clientService.findAllClient();
        return ResponseEntity.status(HttpStatus.OK).body(stores);
    }
    @GetMapping(value = "/{idClient}")
    public ResponseEntity<ClientDto> findByProduct(@PathVariable Long idClient){
        ClientDto store = clientService.findByClient(idClient);
        return ResponseEntity.status(HttpStatus.OK).body(store);
    }
    @PutMapping(value = "/{idClient}")
    public ResponseEntity<ClientDto> updateProduct(@RequestBody RequestClient requestClient, @PathVariable Long idClient){
        ClientDto storeDto = clientService.updateClient(requestClient,idClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeDto);
    }
    @DeleteMapping(value = "/{idClient}")
    public ResponseEntity<ClientDto> deleteProduct(@PathVariable Long idClient){
        ClientDto client = clientService.deleteClient(idClient);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }
}
