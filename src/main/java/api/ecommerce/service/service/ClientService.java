package api.ecommerce.service.service;

import api.ecommerce.service.domains.entity.Client;
import api.ecommerce.service.domains.entity.DeliveryAddress;
import api.ecommerce.service.domains.entity.Store;
import api.ecommerce.service.domains.model.ClientDto;
import api.ecommerce.service.domains.model.DeliveryAddressDto;
import api.ecommerce.service.domains.model.StoreDto;
import api.ecommerce.service.domains.repository.ClientRepository;
import api.ecommerce.service.domains.repository.DeliveryAddressRepository;
import api.ecommerce.service.domains.repository.StoreRepository;
import api.ecommerce.service.exception.handler.HandlerEntityNotFoundException;
import api.ecommerce.service.exception.handler.HandlerError;
import api.ecommerce.service.rest.request.RequestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    public ClientDto createClient (RequestClient requestClient, Long idStore){

            Store store = storeRepository.findById(idStore)
                    .orElseThrow(() -> new HandlerEntityNotFoundException("Store not found com id " + idStore));
        try {
            var getDeliveryAddress = requestClient.getDeliveryAddress();

            DeliveryAddress deliveryAddress = new DeliveryAddress();
            deliveryAddress.setAddress(getDeliveryAddress.getAddress());
            deliveryAddress.setNumber(getDeliveryAddress.getNumber());
            deliveryAddress.setState(getDeliveryAddress.getState());
            deliveryAddress.setDistrict(getDeliveryAddress.getDistrict());
            deliveryAddressRepository.save(deliveryAddress);

            Client client = new Client();
            client.setStore(store);
            client.setName(requestClient.getName());
            client.setEmail(requestClient.getEmail());
            client.setCpf(requestClient.getCpf());
            client.setAge(requestClient.getAge());
            client.setDateOfBirth(requestClient.getDateOfBirth());
            client.setDeliveryAddress(deliveryAddress);
            client.setTel(phoneNumberFormat(requestClient.getTel()));
            clientRepository.save(client);

            return new ClientDto("Client create successfully");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public List<ClientDto> findAllClient(){
        try {
            List<Client> clients = clientRepository.findAll();
            List<ClientDto> clientDtos = new ArrayList<>();

            clients.forEach(client -> {
                var store = client.getStore();
                var deliveryAddress = client.getDeliveryAddress();
                StoreDto storeDto = StoreDto.builder()
                                            .id(store.getId())
                                            .cnpj(store.getCnpj())
                                            .name(store.getName())
                                            .build();
                DeliveryAddressDto deliveryAddressDto =
                        DeliveryAddressDto.builder()
                                .id(deliveryAddress.getId())
                                .address(deliveryAddress.getAddress())
                                .state(deliveryAddress.getState())
                                .number(deliveryAddress.getNumber())
                                .district(deliveryAddress.getDistrict())
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
                clientDtos.add(clientDto);
            });
            return clientDtos;
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public ClientDto findByClient(Long idClient){
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Client not found com id " + idClient));
        try {
            return   ClientDto.builder()
                                .id(client.getId())
                                .name(client.getName())
                                .email(client.getEmail())
                                .cpf(client.getCpf())
                                .age(client.getAge())
                                .dateOfbirth(client.getDateOfBirth())
                                .tel(client.getTel())
                                .build();
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public ClientDto updateClient (RequestClient requestClient, Long idClient){
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Client not found com id " + idClient));
        try {
            var getDeliveryAddress = requestClient.getDeliveryAddress();

            DeliveryAddress deliveryAddress = new DeliveryAddress();
            deliveryAddress.setAddress(getDeliveryAddress.getAddress());
            deliveryAddress.setNumber(getDeliveryAddress.getNumber());
            deliveryAddress.setState(getDeliveryAddress.getState());
            deliveryAddress.setDistrict(getDeliveryAddress.getDistrict());
            deliveryAddressRepository.save(deliveryAddress);

            client.setName(requestClient.getName());
            client.setEmail(requestClient.getEmail());
            client.setCpf(requestClient.getCpf());
            client.setAge(requestClient.getAge());
            client.setDateOfBirth(requestClient.getDateOfBirth());
            client.setTel(phoneNumberFormat(requestClient.getTel()));
            clientRepository.save(client);

            return new ClientDto("Client update successfully");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public ClientDto deleteClient (Long idClient){
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new HandlerEntityNotFoundException("Client not found com id " + idClient));
        try {
            clientRepository.delete(client);

            return new ClientDto("Client delete successfully");

        }catch (Exception ex) {
            throw new HandlerError(ex.getMessage());
        }
    }
    public String phoneNumberFormat(String phoneNumber) {
        if (phoneNumber.length() >= 11 && phoneNumber.matches("\\d+")) {
            return String.format("(%s)%s-%s",
                    phoneNumber.substring(0, 2),
                    phoneNumber.substring(2, 6),
                    phoneNumber.substring(6, 10));
        } else {
            throw new HandlerError("Invalid phone number, enter numbers only!");
        }
    }
}
