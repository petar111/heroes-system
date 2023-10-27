package com.springpj.heroescontentcreator.mapper;

import com.springpj.heroescontentcreator.model.dto.OriginVersionDto;
import com.springpj.heroescontentcreator.model.version.OriginVersion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-27T13:26:27+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2-ea (Private Build)"
)
@Component
public class OriginVersionMapperImpl implements OriginVersionMapper {

    @Autowired
    private OriginMapper originMapper;

    @Override
    public OriginVersionDto toDto(OriginVersion origin) {
        if ( origin == null ) {
            return null;
        }

        OriginVersionDto originVersionDto = new OriginVersionDto();

        originVersionDto.setId( origin.getId() );
        originVersionDto.setName( origin.getName() );
        originVersionDto.setDescription( origin.getDescription() );
        originVersionDto.setDateCreated( origin.getDateCreated() );

        return originVersionDto;
    }

    @Override
    public OriginVersion toEntity(OriginVersionDto originDto) {
        if ( originDto == null ) {
            return null;
        }

        OriginVersion originVersion = new OriginVersion();

        originVersion.setId( originDto.getId() );
        originVersion.setName( originDto.getName() );
        originVersion.setDescription( originDto.getDescription() );
        originVersion.setOrigin( originMapper.toEntity( originDto.getOrigin() ) );
        originVersion.setDateCreated( originDto.getDateCreated() );

        return originVersion;
    }

    @Override
    public List<OriginVersion> toEntityList(List<OriginVersionDto> originDtos) {
        if ( originDtos == null ) {
            return null;
        }

        List<OriginVersion> list = new ArrayList<OriginVersion>( originDtos.size() );
        for ( OriginVersionDto originVersionDto : originDtos ) {
            list.add( toEntity( originVersionDto ) );
        }

        return list;
    }

    @Override
    public List<OriginVersionDto> toDtoList(List<OriginVersion> origins) {
        if ( origins == null ) {
            return null;
        }

        List<OriginVersionDto> list = new ArrayList<OriginVersionDto>( origins.size() );
        for ( OriginVersion originVersion : origins ) {
            list.add( toDto( originVersion ) );
        }

        return list;
    }
}
