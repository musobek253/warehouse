package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.OutputProduct;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("OutputProduct")
public class OutputProductController {

    private final OutputProductService outputProductService;
    @Autowired
    public OutputProductController(OutputProductService outputProductService) {
        this.outputProductService = outputProductService;
    }
    @PostMapping("/add")
    public ApiResponse addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        return outputProductService.addOutputProduct(outputProductDto);
    }

    @PutMapping("/{id}")
    public ApiResponse editOutputProduct(@PathVariable Integer id,@RequestBody OutputProductDto outputProductDto){
        return outputProductService.editOutputProduct(id, outputProductDto);
    }

    @GetMapping("/outputId/outputId")
    public List<OutputProduct> getByOutputId(@PathVariable Integer outputId){
        return outputProductService.getByOutputId(outputId);
    }
}
