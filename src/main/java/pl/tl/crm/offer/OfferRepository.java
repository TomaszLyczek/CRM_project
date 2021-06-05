package pl.tl.crm.offer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@RepositoryRestResource
public interface OfferRepository extends JpaRepository<Offer,Integer> {
    @Override
    Optional<Offer> findById(Integer integer);
}
