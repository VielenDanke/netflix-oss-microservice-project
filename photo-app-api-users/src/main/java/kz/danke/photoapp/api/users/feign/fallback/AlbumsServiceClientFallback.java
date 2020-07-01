package kz.danke.photoapp.api.users.feign.fallback;

import feign.FeignException;
import kz.danke.photoapp.api.users.feign.AlbumServiceClient;
import kz.danke.photoapp.api.users.ui.model.AlbumResponseModel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AlbumsServiceClientFallback implements AlbumServiceClient {

    private final Throwable throwable;

    public AlbumsServiceClientFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public List<AlbumResponseModel> getAlbums(String id) {
        if (throwable instanceof FeignException && ((FeignException) throwable).status() == 404) {
            log.error(
                    String.format(
                            "404 error took place when getAlbums method was called. Error message %s",
                            throwable.getLocalizedMessage()
                    )
            );
        } else {
            log.error(String.format("Exception is happened %s", throwable.getLocalizedMessage()));
        }
        return new ArrayList<>();
    }
}
