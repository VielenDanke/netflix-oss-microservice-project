package kz.danke.photoapp.api.users.ui.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AlbumResponseModel {

    private String albumId;
    private String userId;
    private String name;
    private String description;
}
