package pl.tl.crm.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/items")
    public ResponseEntity getItems() throws JsonProcessingException {
        List<Item> itemsFromDb = itemRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(itemsFromDb));

    }
}
