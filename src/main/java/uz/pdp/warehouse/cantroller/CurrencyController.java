package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.Currency;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;
    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    @PostMapping("/add")
    public ApiResponse addCurrency(@RequestBody Currency currency){
        return currencyService.addCurrency(currency);
    }

    @PutMapping("/{id}")
    public ApiResponse editCurrency(@PathVariable Integer id,@RequestBody Currency currency){
        return currencyService.editCurrency(id, currency);
    }

    @GetMapping("/all")
    public List<Currency> getCurrency(){
        return currencyService.getCurrency();
    }

    @GetMapping("/{id}")
    public Currency getByIdCurrency(@PathVariable Integer id){
        return currencyService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletedCurrency(@PathVariable Integer id){
        return currencyService.deletedCurrency(id);
    }
}
