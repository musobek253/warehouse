package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.Measurement;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.service.MeasurementService;

import java.util.Set;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController( MeasurementService measurementService) {
        this.measurementService = measurementService;
    }
    @PostMapping("/add")
    public ApiResponse addMeasurement(@RequestBody Measurement measurement){
        return measurementService.addMeasurement(measurement);
    }

    @PutMapping("/{id}")
    public ApiResponse editMeasurement(@PathVariable Integer id,@RequestBody Measurement measurement){
        return measurementService.editMeasurement(id, measurement);
    }

    @GetMapping("/all")
    public Set<Measurement> getMeasurement(){
        return measurementService.getMeasurement();
    }

    @GetMapping("/{id}")
    public Measurement getByIdMeasurement(@PathVariable Integer id){
        return measurementService.getById(id);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedMeasurement(@PathVariable Integer id){
        return measurementService.deletedMeasurement(id);
    }
}
