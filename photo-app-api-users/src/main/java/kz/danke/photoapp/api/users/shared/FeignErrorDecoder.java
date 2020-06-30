package kz.danke.photoapp.api.users.shared;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (methodKey.contains("getAlbums")) {
            return new ResponseStatusException(HttpStatus.valueOf(response.status()), "User albums not found");
        } else {
            return new ResponseStatusException(HttpStatus.valueOf(response.status()), response.reason());
        }
    }
}
