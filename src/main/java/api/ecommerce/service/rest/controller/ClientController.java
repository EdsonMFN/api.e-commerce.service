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
    public ResponseEntity<List<ClientDto>> findAllClient(){
        List<ClientDto> clientDtos = clientService.findAllClient();
        return ResponseEntity.status(HttpStatus.OK).body(clientDtos);
    }
    @GetMapping(value = "/{idClient}")
    public ResponseEntity<ClientDto> findByClient(@PathVariable Long idClient){
        ClientDto client = clientService.findByClient(idClient);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }
    @PutMapping(value = "/{idClient}")
    public ResponseEntity<ClientDto> updateClient(@RequestBody RequestClient requestClient, @PathVariable Long idClient){
        ClientDto clientDto = clientService.updateClient(requestClient,idClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDto);
    }
    @DeleteMapping(value = "/{idClient}")
    public ResponseEntity<ClientDto> deleteClient(@PathVariable Long idClient){
        ClientDto client = clientService.deleteClient(idClient);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }
}
