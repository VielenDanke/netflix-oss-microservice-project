package kz.danke.photoapp.api.users.ui.controller;

import kz.danke.photoapp.api.users.service.UserService;
import kz.danke.photoapp.api.users.shared.UserDto;
import kz.danke.photoapp.api.users.ui.model.CreateUserRequest;
import kz.danke.photoapp.api.users.ui.model.CreateUserResponse;
import kz.danke.photoapp.api.users.ui.model.UserResponseModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final Environment environment;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/status/check")
    public String status() {
        return "Working on port " + environment.getProperty("local.server.port") + ", with token = " + environment.getProperty("app.token_secret");
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userRequest, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);

        CreateUserResponse userResponse = modelMapper.map(createdUser, CreateUserResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PreAuthorize(value = "principal == #userId")
    @PostAuthorize(value = "principal == returnObject.body.userId")
    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponseModel> getUserById(@PathVariable(name = "userId") String userId) {
        UserDto userDto = userService.findUserById(userId);

        UserResponseModel returnValue = modelMapper.map(userDto, UserResponseModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
