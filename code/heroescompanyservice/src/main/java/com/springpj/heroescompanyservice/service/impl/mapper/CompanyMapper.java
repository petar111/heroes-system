package com.springpj.heroescompanyservice.service.impl.mapper;

import java.util.List;

import com.springpj.heroescompanyservice.model.company.CreatureInCompany;
import com.springpj.heroescompanyservice.model.company.HeroInCompany;
import com.springpj.heroescompanyservice.model.dto.CreatureInCompanyDto;
import com.springpj.heroescompanyservice.model.dto.HeroInCompanyDto;
import org.mapstruct.Mapper;

import com.springpj.heroescompanyservice.model.dto.CompanyDto;
import com.springpj.heroescompanyservice.model.company.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	CompanyDto toDto(Company company);
	Company toEntity(CompanyDto companyDto);

	List<Company> toEntityList(List<CompanyDto> companyDtos);
	List<CompanyDto> toDtoList(List<Company> companys);

	CreatureInCompanyDto toCreatureInCompanyDto(CreatureInCompany company);
	CreatureInCompany toCreatureInCompanyEntity(CreatureInCompanyDto companyDto);

	List<CreatureInCompany> toCreatureInCompanyEntityList(List<CreatureInCompanyDto> companyDtos);
	List<CreatureInCompanyDto> toCreatureInCompanyDtoList(List<CreatureInCompany> companys);

	HeroInCompanyDto toHeroInCompanyDto(HeroInCompany company);
	HeroInCompany toHeroInCompanyEntity(HeroInCompanyDto companyDto);

	List<HeroInCompany> toHeroInCompanyEntityList(List<HeroInCompanyDto> companyDtos);
	List<HeroInCompanyDto> toHeroInCompanyDtoList(List<HeroInCompany> companys);

}
