package org.elongocrea.pratiquestage.utils.mappers;

import org.elongocrea.pratiquestage.models.Users;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.elongocrea.pratiquestage.utils.dtos.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    @Autowired
    private AppUtils appUtils;

    public UsersDTO mapToDTO(Users entity) {
        return UsersDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .is_active(entity.is_active())
                .is_block(entity.is_block())
                .is_connected(entity.is_connected())
                .last_connected(appUtils.getLocalDateTime(entity.getLast_connected()))
                .status(entity.getStatus())
                .phone(entity.getPhone())
                .google_id(entity.getGoogle_id())
                .build();
    }

    public Users mapToEntity(UsersDTO dto) {
        return Users.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .is_active(dto.is_active())
                .is_block(dto.is_block())
                .is_connected(dto.is_connected())
                .last_connected(appUtils.getSQLTimestamp(dto.getLast_connected()))
                .status(dto.getStatus())
                .phone(dto.getPhone())
                .google_id(dto.getGoogle_id())
                .build();
    }
}
