package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.Output;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    private final OutputService outputService;
    @Autowired
    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @PostMapping("/add")
    public ApiResponse addOutput(@RequestBody OutputDto outputDto){
        return outputService.addOutput(outputDto);
    }

    @PutMapping("/{id}")
    public ApiResponse editOutput(@PathVariable Integer id,@RequestBody OutputDto outputDto){
        return outputService.editOutput(id,outputDto);
    }

    @GetMapping("/warhouseId/{warhauseId}")
    public List<Output> grtByWarhouse(@PathVariable Integer warhauseId){
        return outputService.getByWarhouse(warhauseId);
    }
    @GetMapping("/{id}")
    public Output getById(@PathVariable Integer id){
        return outputService.getBYId(id);
    }

    @GetMapping("/all")
    public List<Output> getALLOutput(){
        return outputService.getAllOutput();
    }

    @GetMapping("/user/{userId}")
    public List<Output> getByUser(@PathVariable Integer userId){
        return outputService.getByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletedOutput(@PathVariable Integer id){
        return outputService.deletedOutput(id);
    }
}
