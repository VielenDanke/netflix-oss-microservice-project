package kz.danke.photoapp.api.users.service.impl;

import feign.FeignException;
import kz.danke.photoapp.api.users.data.AlbumServiceClient;
import kz.danke.photoapp.api.users.data.UserEntity;
import kz.danke.photoapp.api.users.repository.UserRepository;
import kz.danke.photoapp.api.users.service.UserService;
import kz.danke.photoapp.api.users.shared.UserDto;
import kz.danke.photoapp.api.users.ui.model.AlbumResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
//    private final RestTemplate restTemplate;
    private final Environment environment;
    private final AlbumServiceClient albumServiceClient;


    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(passwordEncoder.encode(userDetails.getPassword()));

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

        UserEntity savedEntity = userRepository.save(userEntity);

        return modelMapper.map(savedEntity, UserDto.class);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));

        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto findUserById(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with ID %s not found", userId)));

//        String albumsUrl = String.format(environment.getProperty("albums.url"), userId);

//        ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate
//                .exchange(
//                        albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
//                        }
//                );
//        List<AlbumResponseModel> albumList = albumsListResponse.getBody();

//        try {
//            albumList = albumServiceClient.getAlbums(userId);
//        } catch (FeignException e) {
//            log.error(e.getLocalizedMessage());
//            albumList = new ArrayList<>();
//        }
        List<AlbumResponseModel> albumList = albumServiceClient.getAlbums(userId);

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        userDto.setAlbums(albumList);

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));

        return new User(
                userEntity.getEmail(),
                userEntity.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }
}
