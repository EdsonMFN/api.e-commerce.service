package api.ecommerce.service.service;

import api.ecommerce.service.domains.entity.Store;
import api.ecommerce.service.domains.model.StoreDto;
import api.ecommerce.service.domains.repository.StoreRepository;
import api.ecommerce.service.exception.handler.HandlerEntityNotFoundException;
import api.ecommerce.service.exception.handler.HandlerError;
import api.ecommerce.service.rest.request.RequestStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public StoreDto createStore (RequestStore requestStore){

       try {
           Store store = new Store();
           store.setName(requestStore.getName());
           store.setCnpj(requestStore.getCnpj());
           storeRepository.save(store);

           return StoreDto.builder()
                           .id(store.getId())
                           .name(store.getName())
                           .cnpj(store.getCnpj())
                           .build();
       }catch (Exception ex){
           throw new HandlerError(ex.getMessage());
       }
    }
    public List<StoreDto> findAllStore(){
        try {
            List<Store> stores = storeRepository.findAll();
            List<StoreDto> storesDto = new ArrayList<>();

            stores.forEach(store -> {

                StoreDto storeDto = StoreDto.builder()
                                            .id(store.getId())
                                            .cnpj(store.getCnpj())
                                            .name(store.getName())
                                            .build();

                storesDto.add(storeDto);
            });
            return storesDto;
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public StoreDto findByStore(Long idStore){
        Store store = storeRepository.findById(idStore)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Store not found com id " + idStore));
           try {
               StoreDto storeDto = StoreDto.builder()
                                           .id(store.getId())
                                           .cnpj(store.getCnpj())
                                           .name(store.getName())
                                           .build();
               return storeDto;
           }catch (Exception ex){
               throw new HandlerError(ex.getMessage());
           }
    }

    public StoreDto updateStore (RequestStore requestStore,Long idStore){
        Store store = storeRepository.findById(idStore)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Store not found com id " + idStore));
        try {
            store.setName(requestStore.getName());
            store.setCnpj(requestStore.getCnpj());
            storeRepository.save(store);

            return StoreDto.builder()
                            .id(store.getId())
                            .name(store.getName())
                            .cnpj(store.getCnpj())
                            .build();
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }

    }
    public String deleteStore (Long idStore){
        Store store = storeRepository.findById(idStore)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Store not found com id " + idStore));
        try {
            storeRepository.delete(store);

            return "successful delete";
        }catch (Exception ex) {
            throw new HandlerError(ex.getMessage());
        }
    }
}
