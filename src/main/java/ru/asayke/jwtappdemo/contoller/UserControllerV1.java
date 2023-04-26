package ru.asayke.jwtappdemo.contoller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asayke.jwtappdemo.dto.UserDTO;
import ru.asayke.jwtappdemo.dto.updateDTO.UpdateFieldDTO;
import ru.asayke.jwtappdemo.dto.updateDTO.UpdateIntFieldDTO;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user/")
@AllArgsConstructor
public class UserControllerV1 {
    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(convertToUserDTO(user));
    }

    @GetMapping("get-user")
    public ResponseEntity<UserDTO> getUser(Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);

        return ResponseEntity.ok(convertToUserDTO(user));
    }


    @PostMapping("/update-fullname")
    public ResponseEntity<HttpStatus> updateFirstName(@RequestBody UpdateFieldDTO firstnameDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        user.setFullName(firstnameDTO.getField());
        userService.save(user);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PostMapping("/update-gender")
    public ResponseEntity<HttpStatus> updateGender(@RequestBody UpdateFieldDTO genderDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        user.setGender(genderDTO.getField());
        userService.save(user);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-email")
    public ResponseEntity<HttpStatus> updateEmail(@RequestBody UpdateFieldDTO emailDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);

        if(userService.existsByEmail(emailDTO.getField()))
            return new ResponseEntity<>(HttpStatus.IM_USED);

        user.setEmail(emailDTO.getField());
        userService.save(user);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-color")
    public ResponseEntity<HttpStatus> updateColor(@RequestBody UpdateIntFieldDTO colorDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);

        user.setColor(colorDTO.getField());
        userService.save(user);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-image")
    public ResponseEntity<HttpStatus> updateImage(@RequestBody UpdateIntFieldDTO imageDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);

        user.setImage(imageDTO.getField());
        userService.save(user);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> deleteUser(Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        userService.deleteById(user.getId());

        return ResponseEntity.ok(HttpStatus.OK);
    }


    private UserDTO convertToUserDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }
}