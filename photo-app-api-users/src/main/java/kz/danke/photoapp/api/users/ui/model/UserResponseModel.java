package kz.danke.photoapp.api.users.ui.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserResponseModel {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<AlbumResponseModel> albums;
}
