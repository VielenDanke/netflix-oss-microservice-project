package kz.danke.photoapp.api.users.service;

import kz.danke.photoapp.api.users.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);

    UserDto findUserByEmail(String email);
}
