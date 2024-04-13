package ma.norsysafr.norsifyApi.repository;


import ma.norsysafr.norsifyApi.entities.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}

