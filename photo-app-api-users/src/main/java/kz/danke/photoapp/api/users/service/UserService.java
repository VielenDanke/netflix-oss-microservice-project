package kz.danke.photoapp.api.users.service;

import kz.danke.photoapp.api.users.shared.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);
}
