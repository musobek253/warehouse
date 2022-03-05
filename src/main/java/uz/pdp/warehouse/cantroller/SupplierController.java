package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.Client;
import uz.pdp.warehouse.entity.tamplet.Supplier;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.ClientDto;
import uz.pdp.warehouse.payload.SupplierDto;
import uz.pdp.warehouse.repositary.SupplierRepository;
import uz.pdp.warehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("supplier")
public class SupplierController {
        private final SupplierService supplierService;
    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/add")
    public ApiResponse addSupplier(@RequestBody SupplierDto supplierDto){
        return supplierService.addSupplier(supplierDto);
    }
    @PutMapping("/{id}")
    public ApiResponse editdSupplier(@PathVariable Integer id,@RequestBody SupplierDto supplierDto){
        return supplierService.editSupplier(id, supplierDto);
    }
    @GetMapping("/warehouse/{warhauseId}")
    public List<Supplier> getByWarhouseId(@PathVariable Integer warhauseId){
        return supplierService.getByWarehouseId(warhauseId);
    }
    @GetMapping("/all")
    public List<Supplier> getAllSupplier(){
        return supplierService.getAllSupplier();
    }

    @GetMapping("/all/{id}")
    public Supplier getById(@PathVariable Integer id){
        return supplierService.getById(id);
    }
}
