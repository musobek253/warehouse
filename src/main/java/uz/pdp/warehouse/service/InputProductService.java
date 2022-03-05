package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.tamplet.*;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.repositary.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    private  final InputProductRepository inputProductRepository;
    private final InputRepository inputRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    @Autowired
    public InputProductService(InputProductRepository inputProductRepository, InputRepository inputRepository, ProductRepository productRepository, WarehouseRepository warehouseRepository) {
        this.inputProductRepository = inputProductRepository;
        this.inputRepository = inputRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }
    public ApiResponse addInputProduct(InputProductDto inputProductDto) {
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new ApiResponse("Input not found",false);
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Product Not found",false);
        InputProduct inputProduct  = new InputProduct();
        inputProduct.setInput(optionalInput.get());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new ApiResponse("OutputProduct added",true);
    }

    public ApiResponse editInputProduct(Integer id,InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new ApiResponse("InputProduct not found",false);
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new ApiResponse("Input not found",false);
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Product Not found",false);
        Input input = optionalInput.get();
        Product product = optionalProduct.get();
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setProduct(product);
        inputProduct.setInput(input);
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProduct.getPrice());
        inputProductRepository.save(inputProduct);
        return new ApiResponse("Successfully edited",true);
    }

    public List<InputProduct> getByInputId(Integer inputId){
        if (inputRepository.findById(inputId).isPresent()){
            return inputProductRepository.getAllByInputId(inputId);
        }
        return new ArrayList<>();
    }
    public List<InputProduct> getByWarehouseId(Integer warehouseId){
        if (warehouseRepository.findById(warehouseId).isPresent()){
            return inputProductRepository.getAllByInput_WarehouseId(warehouseId);
        }
        return new ArrayList<>();
    }

    public InputProduct getById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElseGet(InputProduct::new);
    }

    public ApiResponse deletedInput(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()){
            inputProductRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",false);
        }

        return new ApiResponse("Erorr deleted",false);
    }
}
