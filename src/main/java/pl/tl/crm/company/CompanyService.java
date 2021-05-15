package pl.tl.crm.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyService
{
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/companies")
    public ResponseEntity getCompanies() throws JsonProcessingException {
        List<Company> companies = companyRepository.findAll();

        return ResponseEntity.ok(objectMapper.writeValueAsString(companies));
    }
}
