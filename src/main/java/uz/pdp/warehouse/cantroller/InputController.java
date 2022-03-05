package uz.pdp.warehouse.cantroller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.Input;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    private final InputService inputService;

    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    @PostMapping("/add")
    public ApiResponse addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }
    @PutMapping("/{id}")
    public ApiResponse editInput(@PathVariable Integer id,@RequestBody InputDto inputDto){
        return inputService.editInput(id, inputDto);
    }

    @GetMapping("/warhouse/{warhouseId}")
    public List<Input> getByWarhouse(@PathVariable Integer warhouseId){
        return inputService.getByWarhouse(warhouseId);
    }
    @GetMapping("/{id}")
    public Input getById(@PathVariable Integer id){
        return inputService.getBYId(id);
    }

    @GetMapping("/all")
    public List<Input> all(){
        return inputService.getAllInput();
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletedById(@PathVariable Integer id){
        return inputService.deletedInput(id);
    }
}
