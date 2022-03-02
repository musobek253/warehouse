package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.Product;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productservice;
    @Autowired
    public ProductController(ProductService productservice) {
        this.productservice = productservice;
    }

    @PostMapping("/add")
    public ApiResponse addProduct(@RequestBody ProductDto productDto){
        return productservice.addProduct(productDto);
    }
    @GetMapping("/all")
    public List<Product> getAllProduct(){
        return productservice.getByProduct();
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getByCategoryId(@PathVariable Integer categoryId){
        return productservice.getByCategoryId(categoryId);
    }
    @PutMapping("/{id}")
    public ApiResponse editProduct(@PathVariable Integer id,@RequestBody ProductDto productDto){
        return productservice.editProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletedProduct(@PathVariable Integer id){
        return productservice.deleted(id);
    }
    //
}
