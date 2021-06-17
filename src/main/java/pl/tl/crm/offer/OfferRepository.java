package pl.tl.crm.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


//@RepositoryRestResource
public interface OfferRepository extends JpaRepository<Offer,Integer> {
    @Override
    Optional<Offer> findById(Integer integer);




}
