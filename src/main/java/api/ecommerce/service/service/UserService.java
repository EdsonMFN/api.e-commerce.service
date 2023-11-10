//package api.ecommerce.service.product.service;
//
//import api.ecommerce.service.product.domains.entity.Client;
//import api.ecommerce.service.product.domains.model.ClientDto;
//import api.ecommerce.service.product.domains.model.StoreDto;
//import api.ecommerce.service.product.domains.repository.ClientRepository;
//import api.ecommerce.service.product.rest.request.RequestClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//    public ClientDto creatClient (RequestClient requestClient){
//
//        try {
//            Client client = new Client();
//            client.setName(requestClient.getName());
//            client.setEmail(requestClient.getEmail());
//            client.setCpf(requestClient.getCpf());
//            client.setAge(requestClient.getAge());
//            client.setDateOfbirth(requestClient.getDateOfbirth());
//            client.setAddress(requestClient.getAddress());
//            client.setTel(requestClient.getTel());
//            clientRepository.save(client);
//            var store = client.getStore();
//
//            StoreDto storeDto = StoreDto
//                    .builder()
//                    .id(store.getId())
//                    .cnpj(store.getCnpj())
//                    .name(store.getName())
//                    .build();
//
//            return ClientDto.builder()
//                    .name(client.getName())
//                    .email(client.getEmail())
//                    .cpf(client.getCpf())
//                    .age(client.getAge())
//                    .dateOfbirth(client.getDateOfbirth())
//                    .address(client.getAddress())
//                    .tel(client.getTel())
//                    .storeDto(storeDto)
//                    .build();
//
//        }catch (Exception ex){
//            throw new RuntimeException(ex.getMessage());
//        }
//
//    }
//}
