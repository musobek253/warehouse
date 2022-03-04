package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.tamplet.*;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.repositary.ClientRepository;
import uz.pdp.warehouse.repositary.CurrencyRepository;
import uz.pdp.warehouse.repositary.OutputRepository;
import uz.pdp.warehouse.repositary.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    private final OutputRepository outputRepository;
    private final WarehouseRepository warehouseRepository;
    private final CurrencyRepository currencyRepository;
    private final ClientRepository clientRepository;
    @Autowired
    public OutputService(OutputRepository outputRepository, WarehouseRepository warehouseRepository, CurrencyRepository currencyRepository, ClientRepository clientRepository) {
        this.outputRepository = outputRepository;
        this.warehouseRepository = warehouseRepository;
        this.currencyRepository = currencyRepository;
        this.clientRepository = clientRepository;
    }


    public ApiResponse addOutput(OutputDto outputDto) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(outputDto.getWarehouseId());
        Optional<Currency> currencyOptional = currencyRepository.findById(outputDto.getCurrencyId());
        Optional<Client> clientOptional = clientRepository.findById(outputDto.getClientId());
        
        if (!warehouseOptional.isPresent())
            return new ApiResponse("Warehouse not found",false);
        if (!currencyOptional.isPresent())
            return new ApiResponse("Currency not found",false);
        if (!clientOptional.isPresent())
            return new ApiResponse("Client not found",false);
        Output output = new Output();
        output.setCode(codes());
        output.setClient(clientOptional.get());
        output.setCurrency(currencyOptional.get());
        output.setDate(outputDto.getDate());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setWarehouse(warehouseOptional.get());
        outputRepository.save(output);
        return new ApiResponse("Successfully added",true);
    }

    public ApiResponse editOutput(Integer id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new ApiResponse("Output not found",false);
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(outputDto.getWarehouseId());
        Optional<Currency> currencyOptional = currencyRepository.findById(outputDto.getCurrencyId());
        Optional<Client> clientOptional = clientRepository.findById(outputDto.getClientId());
        if (!warehouseOptional.isPresent())
            return new ApiResponse("Warehouse not found",false);
        if (!currencyOptional.isPresent())
            return new ApiResponse("Currency not found",false);
        if (!clientOptional.isPresent())
            return new ApiResponse("Client not found",false);
        Output output = optionalOutput.get();
        output.setWarehouse(warehouseOptional.get());
        output.setDate(outputDto.getDate());
        output.setCurrency(currencyOptional.get());
        output.setCode(output.getCode());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setClient(clientOptional.get());
        outputRepository.save(output);
        return new ApiResponse("Successfully edited",false);
    }

    public List<Output> getByWarhouse(Integer warhauseId) {
        return outputRepository.getAllByWarehouseId(warhauseId);
    }

    public Output getBYId(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElseGet(Output::new);
    }

    public List<Output> getAllOutput() {
        return outputRepository.findAll();
    }

    public List<Output> getByUserId(Integer userId) {
        return outputRepository.getAllByNative(userId);
    }

    public ApiResponse deletedOutput(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()){
            outputRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }
        return new ApiResponse("Deleted Eror",false);
    }

    public String codes(){
        List<Output> outputs = outputRepository.findAll();
        if (outputs.size() == 0)
            return String.valueOf(1);
        int code = outputs.size() - 1;
        int i = Integer.parseInt(outputs.get(code).getCode().trim());
        return String.valueOf(++i);
    }
}
