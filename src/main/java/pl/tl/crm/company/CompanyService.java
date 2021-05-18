package pl.tl.crm.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    @GetMapping(value = "/companies",params = "name")
    public ResponseEntity getCompany(@Param("name") String name) throws JsonProcessingException {
        Optional<Company> company = companyRepository.findByName(name);
        return ResponseEntity.ok(objectMapper.writeValueAsString(company));
    }

    @PostMapping(value = "/companies")
    public ResponseEntity addCompany(@RequestBody Company newCompany) throws JsonProcessingException {

        Optional<Company> companyFromDb = companyRepository.findByName(newCompany.getName());
        if (companyFromDb.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Company savedCompany = companyRepository.save(newCompany);
        return ResponseEntity.ok(objectMapper.writeValueAsString(savedCompany));
    }
}
