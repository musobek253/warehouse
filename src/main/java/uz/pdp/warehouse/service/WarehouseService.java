package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.tamplet.Warehouse;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.repositary.WarehouseRepository;
import java.util.List;
import java.util.Optional;


@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }
    public ApiResponse addWarehouse(Warehouse warehouse){
        if (warehouseRepository.existsByName(warehouse.getName()))
            return new ApiResponse("Already exist Measurement",false);
        warehouseRepository.save(warehouse);
        return new ApiResponse("Successfully added",true);
    }

    // Change units of Warehouse

    public ApiResponse editWarehouse(Integer id,Warehouse warehouse){
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Warehouse Not found",false);
        Warehouse warehouse1 = byId.get();
        if (warehouseRepository.existsByNameNot(warehouse.getName()))
            return new ApiResponse("Already exist Warehouse Name",false);
        warehouse1.setName(warehouse.getName());
        warehouse1.setActive(warehouse.isActive());
        warehouseRepository.save(warehouse1);
        return new ApiResponse("Successfully edited",true);

    }

    // get Warehouse all

    public List<Warehouse> getWarehouse(){
     return warehouseRepository.findAll();
    }

    // get By Id

    public Warehouse getById(Integer id){
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        return byId.orElseGet(Warehouse::new);
    }

    //deactivation of units of Warehouse

    public ApiResponse deletedWarehouse(Integer id){
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if (byId.isPresent()){
            warehouseRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }
        return new ApiResponse("Deleded eror ",false);
    }
}
