package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.tamplet.*;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.repositary.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    private  final OutputProductRepository outputProductRepository;
    private final OutputRepository outputRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final InputProductRepository inputProductRepository;
    @Autowired
    public OutputProductService(OutputProductRepository outputProductRepository, OutputRepository outputRepository, ProductRepository productRepository, WarehouseRepository warehouseRepository, InputProductRepository inputProductRepository) {
        this.outputProductRepository = outputProductRepository;
        this.outputRepository = outputRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.inputProductRepository = inputProductRepository;
    }

    public ApiResponse addOutputProduct(OutputProductDto outputProductDto) {
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new ApiResponse("Output not found",false);
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Product Not found",false);
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setOutput(optionalOutput.get());
        Output output = optionalOutput.get();
        Warehouse warehouse = output.getWarehouse();
        Integer id = warehouse.getId();
        List<InputProduct> inputProductList = inputProductRepository.getAllByInput_WarehouseId(id);
        int summ = 0;
        for (InputProduct inputProduct : inputProductList) {
            if (inputProduct.getProduct().getId() == outputProductDto.getProductId()){
                summ += inputProduct.getAmount();
            }
        }
        if (summ>outputProductDto.getAmount()){
            outputProduct.setPrice(outputProductDto.getPrice());
            outputProduct.setAmount(outputProductDto.getAmount());
            outputProductRepository.save(outputProduct);
            return new ApiResponse("OutputProduct added",true);

        }
        return new ApiResponse("bazda mahsulotlar bu miqdordan kam ",false);



    }

    public ApiResponse editOutputProduct(Integer id,OutputProductDto outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new ApiResponse("OutputProduct not found",false);
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new ApiResponse("Output not found",false);
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Product Not found",false);
        Output output = optionalOutput.get();
        Product product = optionalProduct.get();
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setProduct(product);
        outputProduct.setOutput(output);
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new ApiResponse("Successfully edited",true);
    }

    public List<OutputProduct> getByOutputId(Integer outputId){
        if (outputRepository.findById(outputId).isPresent()){
            return outputProductRepository.getAllByOutputId(outputId);
        }
        return new ArrayList<>();
    }
    public List<OutputProduct> getByWarehouseId(Integer warehouseId){
        if (warehouseRepository.findById(warehouseId).isPresent()){
            return outputProductRepository.getAllByOutput_WarehouseId(warehouseId);
        }
        return new ArrayList<>();
    }

    public ApiResponse deletedOutput(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()){
            outputProductRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",false);
        }

        return new ApiResponse("Erorr deleted",false);




    }
}
