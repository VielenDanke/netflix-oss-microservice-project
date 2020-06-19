package kz.danke.photoapp.api.users.repository;

import kz.danke.photoapp.api.users.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
