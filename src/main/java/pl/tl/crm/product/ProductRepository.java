package pl.tl.crm.product;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tl.crm.offer.Offer;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
