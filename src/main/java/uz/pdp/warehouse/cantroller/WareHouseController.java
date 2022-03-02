package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.Warehouse;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.service.WarehouseService;
import java.util.List;


@RestController
@RequestMapping("/warehouse")
public class WareHouseController {
    private final WarehouseService warehouseService;

    @Autowired
    public WareHouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/add")
    public ApiResponse addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }

    @PutMapping("/{id}")
    public ApiResponse editWarehouse(@PathVariable Integer id,@RequestBody Warehouse warehouse){
        return warehouseService.editWarehouse(id, warehouse);
    }

    @GetMapping("/all")
    public List<Warehouse> getWarehouse(){
        return warehouseService.getWarehouse();
    }

    @GetMapping("/{id}")
    public Warehouse getByIdWarehouse(@PathVariable Integer id){
        return warehouseService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletedWareHouse(@PathVariable Integer id){
        return warehouseService.deletedWarehouse(id);
    }

}
