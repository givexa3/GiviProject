package com.example.giviproject.controller;

import com.example.giviproject.dto.CompanyDTO;
import com.example.giviproject.dto.JobDTO;
import com.example.giviproject.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company")
    public ResponseEntity<CompanyDTO> getCompany(@RequestParam Long companyId)
    {
        return ResponseEntity.ok(companyService.getCompany(companyId));
    }

    @PostMapping("/company")
    public ResponseEntity<HttpStatus> createCompany(@RequestBody CompanyDTO companyDTO)
    {
        //we need to reimplement this looks awful :D
        companyService.createCompany(companyDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies()
    {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
}
