package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.tamplet.Measurement;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.repositary.MeasurementRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    // add units of measurement

    public ApiResponse addMeasurement(Measurement measurement){
        if (measurementRepository.existsByName(measurement.getName()))
            return new ApiResponse("Already exist Measurement",false);
        measurementRepository.save(measurement);
        return new ApiResponse("Successfully added",true);
    }

    // Change units of measurement

    public ApiResponse editMeasurement(Integer id,Measurement measurement){
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (!byId.isPresent())


            return new ApiResponse("Measurement Not found",false);
        Measurement measurement1 = byId.get();
        if (measurementRepository.existsByNameNot(measurement.getName()))
            return new ApiResponse("Already exist Measurement Name",false);
        measurement1.setName(measurement.getName());
        measurement1.setActive(measurement.isActive());
        measurementRepository.save(measurement1);
        return new ApiResponse("Successfully edited",true);

    }

    // get measurement all

    public Set<Measurement> getMeasurement(){
        List<Measurement> all = measurementRepository.findAll();
        return new HashSet<>(all);
    }

    // get By Id

    public Measurement getById(Integer id){
        Optional<Measurement> byId = measurementRepository.findById(id);
        return byId.orElseGet(Measurement::new);
    }

    //deactivation of units of measurement

    public ApiResponse deletedMeasurement(Integer id){
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isPresent()){
            measurementRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }
        return new ApiResponse("Deleded eror ",false);
    }
}
