package com.example.giviproject.service;

import com.example.giviproject.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> getAllCompanies();
    CompanyDTO getCompany(long companyId);
    void createCompany(CompanyDTO companyDTO);
}
