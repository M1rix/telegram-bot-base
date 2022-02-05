package uz.apextech.basebot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.apextech.basebot.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
