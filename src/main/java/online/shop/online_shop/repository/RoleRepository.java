package online.shop.online_shop.repository;

import online.shop.online_shop.entity.Role;
import online.shop.online_shop.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role  r where r.roleName = ?1")
    Role getByRoleName(RoleName roleName);
}
