package kz.danke.photoapp.api.users.feign.fallback.factory;

import feign.hystrix.FallbackFactory;
import kz.danke.photoapp.api.users.feign.AlbumServiceClient;
import kz.danke.photoapp.api.users.feign.fallback.AlbumsServiceClientFallback;
import org.springframework.stereotype.Component;

@Component
public class AlbumsFallbackFactory implements FallbackFactory<AlbumServiceClient> {

    @Override
    public AlbumServiceClient create(Throwable throwable) {
        return new AlbumsServiceClientFallback(throwable);
    }
}
