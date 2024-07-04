package org.elongocrea.pratiquestage.services;

import org.elongocrea.pratiquestage.models.Users;
import org.elongocrea.pratiquestage.utils.dtos.UsersDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Locale;

public interface UsersService {
    Users save(UsersDTO dto, BindingResult result, Locale locale);

    void delete(int id, Locale locale);

    Users getById(int id);

    List<Users> get(boolean isActive, boolean isBlock, boolean isConnected, String keyword);

    List<Users> getAll();
}
