package com.springpj.heroescontentcreator.mapper;

import com.springpj.heroescontentcreator.model.authorization.Role;
import com.springpj.heroescontentcreator.model.dto.RegisterRequestDto;
import com.springpj.heroescontentcreator.model.dto.UserDto;
import com.springpj.heroescontentcreator.model.user.User;
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
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setEmail( user.getEmail() );
        userDto.setBackupEmail( user.getBackupEmail() );
        userDto.setPassword( user.getPassword() );
        userDto.setAccountStatus( user.getAccountStatus() );
        userDto.setDateCreated( user.getDateCreated() );
        userDto.setDateLastUpdated( user.getDateLastUpdated() );
        userDto.setCredentialsExpired( user.isCredentialsExpired() );

        return userDto;
    }

    @Override
    public List<UserDto> toListDto(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setRole( userDtoToRole( userDto ) );
        user.setId( userDto.getId() );
        user.setUsername( userDto.getUsername() );
        user.setEmail( userDto.getEmail() );
        user.setBackupEmail( userDto.getBackupEmail() );
        user.setPassword( userDto.getPassword() );
        user.setAccountStatus( userDto.getAccountStatus() );
        user.setDateCreated( userDto.getDateCreated() );
        user.setDateLastUpdated( userDto.getDateLastUpdated() );
        user.setCredentialsExpired( userDto.isCredentialsExpired() );

        return user;
    }

    @Override
    public User toEntity(RegisterRequestDto registerRequestDto) {
        if ( registerRequestDto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( registerRequestDto.getUsername() );
        user.setEmail( registerRequestDto.getEmail() );
        user.setBackupEmail( registerRequestDto.getBackupEmail() );
        user.setPassword( registerRequestDto.getPassword() );

        return user;
    }

    protected Role userDtoToRole(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( userDto.getRoleId() );

        return role;
    }
}
