package clearsolution.pytaichuk.test.controller;

import clearsolution.pytaichuk.test.dto.UserDTO;
import clearsolution.pytaichuk.test.dto.request.UserBirthRange;
import clearsolution.pytaichuk.test.dto.request.UserEmail;
import clearsolution.pytaichuk.test.service.UserService;
import clearsolution.pytaichuk.test.util.exception.BindingExceptionHandler;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final BindingExceptionHandler bindingExceptionHandler;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingExceptionHandler.ex(bindingResult));
        }

        userService.createUser(userDTO);

        return "User: \"" + userDTO.getEmail() + "\" successfully created!";
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingExceptionHandler.ex(bindingResult));
        }

        userService.updateUser(id, userDTO);

        return "User: \"" + userDTO.getEmail() + "\" successfully updated!";
    }

    @GetMapping()
    public List<UserDTO> getUsersByBirthRange(@RequestBody UserBirthRange userBirthRange){

        if(userBirthRange.getFrom().isAfter(userBirthRange.getTo())){
            throw new ValidationException("date - Date from cannot be greater than Date to;");
        }

        return userService.searchUserByBirthRange(userBirthRange);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@RequestBody @Valid UserEmail userEmail, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingExceptionHandler.ex(bindingResult));
        }

        userService.deleteUser(userEmail);

        return "User: \"" + userEmail.getEmail() + "\" successfully deleted!";
    }
}
