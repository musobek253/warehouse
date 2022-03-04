package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.tamplet.Client;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.ClientDto;
import uz.pdp.warehouse.repositary.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ApiResponse addClient(ClientDto clientDto) {
        if (clientRepository.existsByPhoneNumber(clientDto.getPhoneNumber()))
            return new ApiResponse("Already exist PhoneNumber",false);
        Client client = new Client();

        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setActive(clientDto.isActive());
        clientRepository.save(client);
        return new ApiResponse("Successfully added",true);
    }

    public ApiResponse editClient(Integer id,ClientDto clientDto){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new ApiResponse("Client not found",false);
        if (clientRepository.existsByPhoneNumberNot(clientDto.getPhoneNumber()))
            return new ApiResponse("Already exist PhoneNumber",false);
        Client client = optionalClient.get();
        client.setActive(clientDto.isActive());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepository.save(client);
        return new ApiResponse("Successfully edited",true);
    }
//    get By Warehouse id

    public List<Client> getByWarehouseId(Integer id){
        return clientRepository.getAllByNative(id);
    }

    public List<Client> getAllClient(){
        return clientRepository.findAll();
    }

    public Client getById(Integer integer){
        Optional<Client> byId = clientRepository.findById(integer);
        return byId.orElseGet(Client::new);
    }

    public ApiResponse deletedClient(Integer id){
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()){
            clientRepository.deleteById(id);
            return new ApiResponse("successfully edited",true);
        }
        return new ApiResponse("Deleted Eror",false);
    }

}
