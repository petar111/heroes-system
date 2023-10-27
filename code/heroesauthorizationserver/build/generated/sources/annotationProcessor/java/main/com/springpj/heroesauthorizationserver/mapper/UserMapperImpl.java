package com.springpj.heroesauthorizationserver.mapper;

import com.springpj.heroesauthorizationserver.dto.RegisterRequestDto;
import com.springpj.heroesauthorizationserver.dto.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-27T09:25:10+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 18.0.2-ea (Private Build)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(RegisterRequestDto registerRequestDto) {
        if ( registerRequestDto == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUsername( registerRequestDto.getUsername() );
        userDto.setEmail( registerRequestDto.getEmail() );
        userDto.setBackupEmail( registerRequestDto.getBackupEmail() );
        userDto.setPassword( registerRequestDto.getPassword() );

        return userDto;
    }
}
