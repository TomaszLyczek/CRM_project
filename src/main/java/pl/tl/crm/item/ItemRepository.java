package pl.tl.crm.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item,Integer> {
    @Override
    List<Item> findAll();
}
