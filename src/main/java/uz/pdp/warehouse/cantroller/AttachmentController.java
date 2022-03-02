package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.service.AttachmentService;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    private final AttachmentService attachmentService;
    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }
    @PostMapping("/upload")
    public ApiResponse addFile(MultipartHttpServletRequest request){
        return attachmentService.addAttachment(request);
    }

    @GetMapping("/dowlond/{id}")
    public ApiResponse getFile(@PathVariable Integer id, HttpServletResponse response){
        return attachmentService.getFile(id, response);
    }

    @PutMapping("/{id}")
    public ApiResponse editfile(@PathVariable Integer id,MultipartHttpServletRequest request){
        return attachmentService.editUpload(id, request);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedFile(@PathVariable Integer id){
        return attachmentService.deletedAttachment(id);
    }
}
