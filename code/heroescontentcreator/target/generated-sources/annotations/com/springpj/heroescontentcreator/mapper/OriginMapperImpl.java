package com.springpj.heroescontentcreator.mapper;

import com.springpj.heroescontentcreator.model.dto.OriginDto;
import com.springpj.heroescontentcreator.model.origin.Origin;
import com.springpj.heroescontentcreator.model.version.OriginVersion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-30T09:24:15+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2 (Eclipse Adoptium)"
)
@Component
public class OriginMapperImpl implements OriginMapper {

    @Override
    public OriginDto toDto(Origin origin) {
        if ( origin == null ) {
            return null;
        }

        OriginDto originDto = new OriginDto();

        originDto.setId( origin.getId() );
        originDto.setName( origin.getName() );
        originDto.setDescription( origin.getDescription() );
        originDto.setDateCreated( origin.getDateCreated() );
        originDto.setDateLastUpdated( origin.getDateLastUpdated() );

        return originDto;
    }

    @Override
    public Origin toEntity(OriginDto originDto) {
        if ( originDto == null ) {
            return null;
        }

        Origin origin = new Origin();

        origin.setId( originDto.getId() );
        origin.setName( originDto.getName() );
        origin.setDescription( originDto.getDescription() );
        origin.setDateCreated( originDto.getDateCreated() );
        origin.setDateLastUpdated( originDto.getDateLastUpdated() );

        return origin;
    }

    @Override
    public OriginVersion toVersionEntity(Origin origin) {
        if ( origin == null ) {
            return null;
        }

        OriginVersion originVersion = new OriginVersion();

        originVersion.setOrigin( origin );
        originVersion.setName( origin.getName() );
        originVersion.setDescription( origin.getDescription() );

        return originVersion;
    }

    @Override
    public List<Origin> toEntityList(List<OriginDto> originDtos) {
        if ( originDtos == null ) {
            return null;
        }

        List<Origin> list = new ArrayList<Origin>( originDtos.size() );
        for ( OriginDto originDto : originDtos ) {
            list.add( toEntity( originDto ) );
        }

        return list;
    }

    @Override
    public List<OriginDto> toDtoList(List<Origin> origins) {
        if ( origins == null ) {
            return null;
        }

        List<OriginDto> list = new ArrayList<OriginDto>( origins.size() );
        for ( Origin origin : origins ) {
            list.add( toDto( origin ) );
        }

        return list;
    }
}
