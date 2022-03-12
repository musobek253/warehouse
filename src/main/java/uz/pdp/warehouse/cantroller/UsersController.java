package uz.pdp.warehouse.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.tamplet.User;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.UsersDto;
import uz.pdp.warehouse.service.UsesrSrevice;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    private final UsesrSrevice usesrSrevice;
    @Autowired
    public UsersController(UsesrSrevice usesrSrevice) {
        this.usesrSrevice = usesrSrevice;
    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody UsersDto usersDto)
    {
        return usesrSrevice.addUser(usersDto);
    }
    @PutMapping("/{id}")
    public ApiResponse eidtUser(@PathVariable Integer id,@RequestBody UsersDto usersDto){
        return usesrSrevice.editUser(id, usersDto);
    }
    @GetMapping("/war/{warhouseId}")
    public List<User> getWarhouseId(@PathVariable Integer warhouseId){
        return usesrSrevice.getWarehouse(warhouseId);
    }

}
