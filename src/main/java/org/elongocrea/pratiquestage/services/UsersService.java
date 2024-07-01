package org.elongocrea.pratiquestage.services;

import org.elongocrea.pratiquestage.models.Users;
import org.elongocrea.pratiquestage.utils.dtos.UsersDTO;
import org.springframework.validation.BindingResult;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public interface UsersService {

    Users save(UsersDTO dto, BindingResult result, Locale locale);

    List<Users> delete(int id, Locale locale);

    Users getById(int id);

    List<Users> get(boolean is_active, boolean is_block, boolean is_connected, String keyword);

}
