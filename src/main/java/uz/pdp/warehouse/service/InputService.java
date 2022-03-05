package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.tamplet.*;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.repositary.CurrencyRepository;
import uz.pdp.warehouse.repositary.InputRepository;
import uz.pdp.warehouse.repositary.SupplierRepository;
import uz.pdp.warehouse.repositary.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    private final InputRepository inputRepository;
    private final WarehouseRepository warehouseRepository;
    private final SupplierRepository supplierRepository;
    private final CurrencyRepository currencyRepository;
    @Autowired
    public InputService(InputRepository inputRepository, WarehouseRepository warehouseRepository, SupplierRepository supplierRepository, CurrencyRepository currencyRepository) {
        this.inputRepository = inputRepository;
        this.warehouseRepository = warehouseRepository;
        this.supplierRepository = supplierRepository;
        this.currencyRepository = currencyRepository;
    }
    public ApiResponse addInput(InputDto inputDto) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(inputDto.getWarehouseId());
        Optional<Currency> currencyOptional = currencyRepository.findById(inputDto.getCurrencyId());
        Optional<Supplier> supplierOptional = supplierRepository.findById(inputDto.getSupplierId());

        if (!warehouseOptional.isPresent())
            return new ApiResponse("Warehouse not found",false);
        if (!currencyOptional.isPresent())
            return new ApiResponse("Currency not found",false);
        if (!supplierOptional.isPresent())
            return new ApiResponse("Supplier not found",false);
        Input input = new Input();
        input.setCode(codes());
        input.setSupplier(supplierOptional.get());
        input.setCurrency(currencyOptional.get());
        input.setDate(inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setWarehouse(warehouseOptional.get());
        inputRepository.save(input);
        return new ApiResponse("Successfully added",true);
    }

    public ApiResponse editInput(Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new ApiResponse("Input not found",false);
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(inputDto.getWarehouseId());
        Optional<Currency> currencyOptional = currencyRepository.findById(inputDto.getCurrencyId());
        Optional<Supplier> supplierOptional = supplierRepository.findById(inputDto.getSupplierId());
        if (!warehouseOptional.isPresent())
            return new ApiResponse("Warehouse not found",false);
        if (!currencyOptional.isPresent())
            return new ApiResponse("Currency not found",false);
        if (!supplierOptional.isPresent())
            return new ApiResponse("Supplier not found",false);
        Input input = optionalInput.get();
        input.setWarehouse(warehouseOptional.get());
        input.setDate(inputDto.getDate());
        input.setCurrency(currencyOptional.get());
        input.setCode(input.getCode());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setSupplier(supplierOptional.get());
        inputRepository.save(input);
        return new ApiResponse("Successfully edited",false);
    }

    public List<Input> getByWarhouse(Integer warhauseId) {
        return inputRepository.getAllByWarehouseId(warhauseId);
    }

    public Input getBYId(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElseGet(Input::new);
    }

    public List<Input> getAllInput() {
        return inputRepository.findAll();
    }

    public ApiResponse deletedInput(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()){
            inputRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }
        return new ApiResponse("Deleted Eror",false);
    }

    public String codes(){
        List<Input> inputs = inputRepository.findAll();
        if (inputs.size() == 0)
            return String.valueOf(1);
        int code = inputs.size() - 1;
        int i = Integer.parseInt(inputs.get(code).getCode().trim());
        return String.valueOf(++i);
    }


}
