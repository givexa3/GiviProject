package com.example.giviproject.service.Impl;

import com.example.giviproject.dto.CompanyDTO;
import com.example.giviproject.model.Company;
import com.example.giviproject.repository.CompanyRepository;
import com.example.giviproject.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        List<Company> companies = (List<Company>) companyRepository.findAll();

        for (Company company : companies) {
            CompanyDTO companyDTO = CompanyDTO
                    .builder()
                    .companyName(company.getCompanyName())
                    .companyDescription(company.getCompanyDescription())
                    .jobDTOList(company.getJobs())
                    .build();

            companyDTOList.add(companyDTO);
        }


        return companyDTOList;
    }

    @Override
    public CompanyDTO getCompany(long companyId) {

        Optional<Company> company = companyRepository.findById(companyId);

        if (company.isEmpty())
        {
            //need custom exception here
            throw new NullPointerException();
        }

        return CompanyDTO
                .builder()
                .companyName(company.get().getCompanyName())
                .companyDescription(company.get().getCompanyDescription())
                .jobDTOList(company.get().getJobs())
                .build();
    }

    @Override
    public void createCompany(CompanyDTO companyDTO)
    {
        Company company = Company
                .builder()
                .companyName(companyDTO.getCompanyName())
                .companyDescription(companyDTO.getCompanyDescription())
                .jobs(companyDTO.getJobDTOList())
                .build();

        companyRepository.save(company);
    }
}
