package ru.asayke.jwtappdemo.contoller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asayke.jwtappdemo.dto.AdminUserDTO;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.service.UserService;

@RestController
@RequestMapping(value = "/admin/")
@AllArgsConstructor
public class AdminControllerV1 {
    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDTO> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(convertToAdminUserDTO(user));
    }

    private AdminUserDTO convertToAdminUserDTO(User user) {
        return mapper.map(user, AdminUserDTO.class);
    }
}