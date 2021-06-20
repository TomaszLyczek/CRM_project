package pl.tl.crm.product;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Override
    Optional<Product> findById(Integer integer);

    Optional<Product> findByName(final String name);

    List<Product> findByNameContaining(final String name);

    List<Product> findByDescriptionContaining(final String description);

    List<Product> findByPriceBetween(final Integer min, final Integer max);

}
