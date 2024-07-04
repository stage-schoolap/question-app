package org.elongocrea.pratiquestage.utils.mappers;

import org.elongocrea.pratiquestage.models.Users;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.elongocrea.pratiquestage.utils.dtos.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Component
@Mapper
public class UsersMapper {

    @Autowired
    private AppUtils appUtils;

    // UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    // UsersDTO mapToDTO(Users user);
//
    // Users mapToEntity(UsersDTO dto);

    public UsersDTO mapToDTO(Users entity) {
        return UsersDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .isActive(entity.isActive())
                .isBlock(entity.isBlock())
                .isConnected(entity.isConnected())
                .lastConnected(appUtils.getLocalDateTime(entity.getLastConnected()))
                .status(entity.getStatus())
                .phone(entity.getPhone())
                .googleId(entity.getGoogleId())
                .build();
    }

    public Users mapToEntity(UsersDTO dto) {
        return Users.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .isActive(dto.getIsActive())
                .isBlock(dto.getIsBlock())
                .isConnected(dto.getIsConnected())
                .lastConnected(appUtils.getSQLTimestamp(dto.getLastConnected()))
                .status(dto.getStatus())
                .phone(dto.getPhone())
                .googleId(dto.getGoogleId())
                .build();
    }
}
