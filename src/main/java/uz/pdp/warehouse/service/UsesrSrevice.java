package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.tamplet.User;
import uz.pdp.warehouse.entity.tamplet.Warehouse;
import uz.pdp.warehouse.payload.ApiResponse;
import uz.pdp.warehouse.payload.UsersDto;
import uz.pdp.warehouse.repositary.UserRepository;
import uz.pdp.warehouse.repositary.WarehouseRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsesrSrevice {
    private final WarehouseRepository warehouseRepository;
    private final UserRepository userRepository;
    @Autowired
    public UsesrSrevice(WarehouseRepository warehouseRepository, UserRepository userRepository) {
        this.warehouseRepository = warehouseRepository;
        this.userRepository = userRepository;
    }

    public ApiResponse addUser(UsersDto usersDto){
        if (userRepository.existsByPhoneNumber(usersDto.getPhoneNumber()))
            return new ApiResponse("Already exist phoneNumber",false);
        if (!warehouseRepository.existsByIdIn(usersDto.getWarehousesId()))
            return new ApiResponse("Not found warhouse",false);
        User user = new User();
        user.setCode(codes());
        user.setFirstName(usersDto.getFirstName());
        user.setPassword(usersDto.getPassword());
        user.setPhoneNumber(usersDto.getPhoneNumber());
        user.setLastName(usersDto.getLastName());
        user.setWarehouses(warehouseRepository.findAllByIdIn(usersDto.getWarehousesId()));
        userRepository.save(user);
        return new ApiResponse("Succsesfully added",true);

    }

    public ApiResponse editUser(Integer id, UsersDto usersDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("User not found",false);
        if (userRepository.existsByPhoneNumberAndIdNot(usersDto.getPhoneNumber(),id))
            return new ApiResponse("Already exist PhoneNumber",false);
        if (!warehouseRepository.existsByIdIn(usersDto.getWarehousesId()))
            return new ApiResponse("Warhouse not found",false);
        User user = optionalUser.get();
        user.setWarehouses(warehouseRepository.findAllByIdIn(usersDto.getWarehousesId()));
        user.setPassword(usersDto.getPassword());
        user.setFirstName(usersDto.getFirstName());
        user.setLastName(usersDto.getLastName());
        user.setPhoneNumber(usersDto.getPhoneNumber());
        userRepository.save(user);
        return new ApiResponse("Successfully edited",true);

    }

    public List<User> getWarehouse(Integer id){
        return userRepository.getAllByNative(id);
    }
    public String codes(){
        List<User> users = userRepository.findAll();
        if (users.size() == 0)
            return String.valueOf(1);
        int code = users.size() - 1;
        int i = Integer.parseInt(users.get(code).getCode().trim());
        return String.valueOf(++i);
    }
}
