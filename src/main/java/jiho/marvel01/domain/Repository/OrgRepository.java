package jiho.marvel01.domain.Repository;

import jiho.marvel01.domain.Entity.OrgEntity;
import jiho.marvel01.domain.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRepository extends JpaRepository<OrgEntity, Long> {
}