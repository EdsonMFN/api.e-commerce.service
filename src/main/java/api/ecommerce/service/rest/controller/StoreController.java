package api.ecommerce.service.rest.controller;

import api.ecommerce.service.domains.model.StoreDto;
import api.ecommerce.service.rest.request.RequestStore;
import api.ecommerce.service.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody RequestStore requestStore){
        StoreDto storeDto = storeService.createStore(requestStore);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeDto);
    }
    @GetMapping
    public ResponseEntity<List<StoreDto>> findAllStore(){
        List<StoreDto> stores = storeService.findAllStore();
        return ResponseEntity.status(HttpStatus.OK).body(stores);
    }
    @GetMapping(value = "/{idStore}")
    public ResponseEntity<StoreDto> findByStore(@PathVariable Long idStore){
        StoreDto store = storeService.findByStore(idStore);
        return ResponseEntity.status(HttpStatus.OK).body(store);
    }
    @PutMapping(value = "/{idStore}")
    public ResponseEntity<StoreDto> updateStore(@RequestBody RequestStore requestStore,@PathVariable Long idStore){
        StoreDto storeDto = storeService.updateStore(requestStore,idStore);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeDto);
    }
    @DeleteMapping(value = "/{idStore}")
    public ResponseEntity<String> deletStore(@PathVariable Long idStore){
        String store = storeService.deleteStore(idStore);
        return ResponseEntity.status(HttpStatus.OK).body(store);
    }
}
