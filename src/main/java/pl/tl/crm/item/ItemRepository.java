package pl.tl.crm.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item,Integer> {
    @Override
    List<Item> findAll();
}
