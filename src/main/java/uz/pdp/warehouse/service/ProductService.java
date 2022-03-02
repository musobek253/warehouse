package uz.pdp.warehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.tamplet.Attachment;
import uz.pdp.warehouse.entity.tamplet.Category;
import uz.pdp.warehouse.entity.tamplet.Measurement;
import uz.pdp.warehouse.entity.tamplet.Product;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.repositary.AttachmentRepository;
import uz.pdp.warehouse.repositary.CategoryRepository;
import uz.pdp.warehouse.repositary.MeasurementRepository;
import uz.pdp.warehouse.repositary.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;
    private final MeasurementRepository measurementRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, AttachmentRepository attachmentRepository, MeasurementRepository measurementRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attachmentRepository = attachmentRepository;
        this.measurementRepository = measurementRepository;
    }

    public ApiResponse addProduct(ProductDto productDto) {
        if (productRepository.existsByName(productDto.getName()))
            return new ApiResponse("Already exist Product name",false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Category not found",false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("photo not found",false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new ApiResponse("measurement not found",false);
        Product product = new Product();
        if (productRepository.existsByCode(codes()))
            return new ApiResponse("such a digital product is available",false);
        product.setCode(codes());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        product.setName(productDto.getName());
        productRepository.save(product);
        return new ApiResponse("Successfully added",true);
    }


    public List<Product> getByProduct(){
        return productRepository.findAll();
    }

    // get by Category
    public List<Product> getByCategoryId(Integer categoryId){
        return productRepository.getAllByCategoryId(categoryId);
    }
    public ApiResponse editProduct(Integer id,ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent())
            return new ApiResponse("Product not found",false);
        if(!productRepository.existsByNameNot(productDto.getName()))
            return new ApiResponse("Already exist Product Name",false);
        if (!categoryRepository.findById(productDto.getCategoryId()).isPresent())
            return new ApiResponse("Category not found",false);
        if (!measurementRepository.findById(productDto.getMeasurementId()).isPresent())
            return new ApiResponse("measurement not found",false);
        if (!attachmentRepository.findById(productDto.getPhotoId()).isPresent())
            return new ApiResponse("photo not found",false);
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setMeasurement(measurementRepository.findById(productDto.getMeasurementId()).get());
        product.setCategory(categoryRepository.findById(productDto.getCategoryId()).get());
        product.setPhoto(attachmentRepository.findById(productDto.getPhotoId()).get());
        product.setCode(product.getCode());
        productRepository.save(product);
        return new ApiResponse("Successfully edited",true);
    }

    public ApiResponse deleted(Integer id){
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()){
            productRepository.deleteById(id);
            return new ApiResponse("successfully deleted",true);
        }
        return new ApiResponse("Deleted eror",false);
    }





    public String codes(){
        List<Product> products = productRepository.findAll();
        if (products.size() == 0)
            return String.valueOf(1);
        int code = products.size() - 1;
        int i = Integer.parseInt(products.get(code).getCode().trim());
        return String.valueOf(++i);
    }
}
