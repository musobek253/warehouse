package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.Client;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.ClientDto;
import uz.pdp.warehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
@Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping("/add")
    public ApiResponse addClient(@RequestBody ClientDto clientDto){
        return clientService.addClient(clientDto);
    }
    @PutMapping("/{id}")
    public ApiResponse editdClient(@PathVariable Integer id,@RequestBody ClientDto clientDto){
        return clientService.editClient(id, clientDto);
    }
    @GetMapping("/warehouse/{warhauseId}")
    public List<Client> getByWarhouseId(@PathVariable Integer warhauseId){
        return clientService.getByWarehouseId(warhauseId);
    }
    @GetMapping("/all")
    public List<Client> getAllClient(){
        return clientService.getAllClient();
    }

    @GetMapping("/all/{id}")
    public Client getById(@PathVariable Integer id){
        return clientService.getById(id);
    }
}
