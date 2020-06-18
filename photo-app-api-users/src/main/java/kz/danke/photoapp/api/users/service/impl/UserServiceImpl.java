package kz.danke.photoapp.api.users.service.impl;

import kz.danke.photoapp.api.users.service.UserService;
import kz.danke.photoapp.api.users.shared.UserDto;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        return null;
    }
}
