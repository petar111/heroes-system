package com.springpj.heroescompanyservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.springpj.heroescompanyservice.model.dto.CompanyDto;
import com.springpj.heroescompanyservice.model.company.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	CompanyDto toDto(Company company);
	Company toEntity(CompanyDto companyDto);

	List<Company> toEntityList(List<CompanyDto> companyDtos);
	List<CompanyDto> toDtoList(List<Company> companys);

}
