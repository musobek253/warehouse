package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.tamplet.Currency;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.repositary.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }
    public ApiResponse addCurrency(Currency currency){
        if (currencyRepository.existsByName(currency.getName()))
            return new ApiResponse("Already exist Currency",false);
        currencyRepository.save(currency);
        return new ApiResponse("Successfully added",true);
    }

    // Change units of Currency

    public ApiResponse editCurrency(Integer id,Currency currency){
        Optional<Currency> byId = currencyRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Currency Not found",false);
        Currency currency1 = byId.get();
        if (currencyRepository.existsByNameNot(currency.getName()))
            return new ApiResponse("Already exist Currency name",false);
        currency1.setName(currency.getName());
        currency1.setActive(currency.isActive());
        currencyRepository.save(currency1);
        return new ApiResponse("Successfully edited",true);
    }

    // get Currency all

    public List<Currency> getCurrency(){
        return currencyRepository.findAll();
    }

    // get By Id

    public Currency getById(Integer id){
        Optional<Currency> byId = currencyRepository.findById(id);
        return byId.orElseGet(Currency::new);
    }

    //deactivation of units of Currency

    public ApiResponse deletedCurrency(Integer id){
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()){
            currencyRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }
        return new ApiResponse("Deleded eror ",false);
    }
}
