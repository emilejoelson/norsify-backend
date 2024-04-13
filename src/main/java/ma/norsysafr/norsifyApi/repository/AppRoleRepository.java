package ma.norsysafr.norsifyApi.repository;


import ma.norsysafr.norsifyApi.entities.user.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
