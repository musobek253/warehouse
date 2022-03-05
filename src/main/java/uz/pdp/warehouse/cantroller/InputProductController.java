package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.InputProduct;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    private final InputProductService inputProductService;
    @Autowired
    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody InputProductDto inputProductDto){
        return inputProductService.addInputProduct(inputProductDto);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody InputProductDto inputProductDto){
        return inputProductService.editInputProduct(id, inputProductDto);
    }

    @GetMapping("/{id}")
    public InputProduct getById(@PathVariable Integer id ){
        return inputProductService.getById(id);
    }

    @GetMapping("/warhouse/{warhouseId}")
    public List<InputProduct>getByWarhouseId(@PathVariable Integer warhouseId){
        return inputProductService.getByWarehouseId(warhouseId);
    }
    @GetMapping("/Input/{inputid}")
    public List<InputProduct> getByInputId(@PathVariable Integer inputid){
        return inputProductService.getByInputId(inputid);
    }

    @DeleteMapping("{id}")
    public ApiResponse deletedById(@PathVariable Integer id){
        return inputProductService.deletedInput(id);
    }

}
