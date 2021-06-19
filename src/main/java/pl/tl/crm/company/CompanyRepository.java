package pl.tl.crm.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company,Integer> {

    Optional<Company> findByName(final String name);

    List<Company> findByNameContaining(final String name);
}
