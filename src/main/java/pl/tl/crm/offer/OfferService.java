package pl.tl.crm.offer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tl.crm.company.Company;
import pl.tl.crm.company.CompanyRepository;
import pl.tl.crm.item.Item;
import pl.tl.crm.item.ItemRepository;
import pl.tl.crm.product.Product;
import pl.tl.crm.product.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class OfferService {
    @Autowired
    OfferRepository offerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(value = "/offers")
    public ResponseEntity getOffers() throws JsonProcessingException {
        List<Offer> offer = offerRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(offer));
    }

    //@GetMapping(value = "/offers", params = "id")
    @GetMapping(path = "/offers/{id}")
    public ResponseEntity getOfferById(@PathVariable("id") Integer id) throws JsonProcessingException {
        Optional<Offer> offer = offerRepository.findById(id);
        return ResponseEntity.ok(objectMapper.writeValueAsString(offer));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/offers", params = "companyId")
    ResponseEntity createOfferForCompany(@Param("companyId") Integer companyId) throws JsonProcessingException {
        if (!companyRepository.existsById(companyId)){
            return ResponseEntity.notFound().build();
        }
        Optional<Company> company = companyRepository.findById(companyId);

        Offer createdOffer = new Offer(company.get());
        createdOffer.setDiscount(0);
        offerRepository.save(createdOffer);

        return ResponseEntity.ok(objectMapper.writeValueAsString(createdOffer));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/offers/{id}", params = {"productId", "quantity"})
    ResponseEntity addItemToOffer(@PathVariable("id") Integer id, @Param("productId") Integer productId,
                                  @Param("quantity") Integer quantity) throws JsonProcessingException {

        Optional<Offer> offerFromRepositoryById = offerRepository.findById(id);
        Optional<Product> productById = productRepository.findById(productId);
        Set<Item> itemsFromOffer = offerFromRepositoryById.get().getItems();
        Integer itemId = null;
        for (Item item: itemsFromOffer) {
            if (item.getProduct().equals(productById.get())){
                quantity += item.getQuantity();
                itemId = item.getId();
            }
        }
        Item itemToSave;
        if (itemId != null) {
            Item itemFromRepository = itemRepository.findById(itemId).get();
            itemFromRepository.setQuantity(quantity);
            itemToSave = itemFromRepository;
            itemRepository.save(itemFromRepository);
        } else{
            Item newItem = new Item(productById.get(),quantity ,offerFromRepositoryById.get());
            itemToSave = newItem;
            itemRepository.save(newItem);
        }
        return ResponseEntity.ok(objectMapper.writeValueAsString(itemToSave));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/offers/{id}")
    ResponseEntity deleteOfferById(@PathVariable("id") Integer id){
        if(offerRepository.existsById(id)){
            offerRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/offers/{id}", params = "itemId")
    ResponseEntity deleteItemFromOffer(@PathVariable("id") Integer id, @Param("itemId") Integer itemId){

        if(offerRepository.existsById(id)){
            offerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
