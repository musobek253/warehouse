package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.tamplet.Client;
import uz.pdp.warehouse.entity.tamplet.Supplier;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.SupplierDto;
import uz.pdp.warehouse.repositary.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public ApiResponse addSupplier(SupplierDto supplierDto) {
        if (supplierRepository.existsByPhoneNumber(supplierDto.getPhoneNumber()))
            return new ApiResponse("Already exist PhoneNumber",false);
        Supplier supplier = new Supplier();

        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplier.setActive(supplierDto.isActive());
        supplierRepository.save(supplier);
        return new ApiResponse("Successfully added",true);
    }

    public ApiResponse editSupplier(Integer id, SupplierDto supplierDto) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new ApiResponse("Supplier not found",false);
        if (supplierRepository.existsByPhoneNumberNot(supplierDto.getPhoneNumber()))
            return new ApiResponse("Already exist PhoneNumber",false);
        Supplier supplier = optionalSupplier.get();
        supplier.setActive(supplierDto.isActive());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplierRepository.save(supplier);
        return new ApiResponse("Successfully edited",true);
    }

    public List<Supplier> getByWarehouseId(Integer warhauseId) {
        return supplierRepository.getAllByNative(warhauseId);
    }

    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    public Supplier getById(Integer id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        return byId.orElseGet(Supplier::new);
    }
    public ApiResponse deletedClient(Integer id){
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isPresent()){
            supplierRepository.deleteById(id);
            return new ApiResponse("successfully edited",true);
        }
        return new ApiResponse("Deleted Eror",false);
    }
}
