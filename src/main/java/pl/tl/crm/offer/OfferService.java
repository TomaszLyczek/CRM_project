package pl.tl.crm.offer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tl.crm.item.ItemRepository;

import java.util.Optional;

@RestController
public class OfferService {
    @Autowired
    OfferRepository offerRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(name = "offers",params = "id")
    public ResponseEntity getOffer(@Param("id") Integer id) throws JsonProcessingException {
        Optional<Offer> offer = offerRepository.findById(id);
        return ResponseEntity.ok(objectMapper.writeValueAsString(offer));
    }

}
