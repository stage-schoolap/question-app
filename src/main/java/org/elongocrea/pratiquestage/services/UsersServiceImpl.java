package org.elongocrea.pratiquestage.services;

import lombok.RequiredArgsConstructor;
import org.elongocrea.pratiquestage.models.Users;
import org.elongocrea.pratiquestage.repositories.UsersRepo;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.elongocrea.pratiquestage.utils.dtos.UsersDTO;
import org.elongocrea.pratiquestage.utils.exceptions.MyDuplicatedEntryException;
import org.elongocrea.pratiquestage.utils.mappers.UsersMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepo repo;
    private final UsersMapper mapper;
    private final AppUtils utils;
    private final MessageSource msgSrc;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Users save(UsersDTO dto, BindingResult result, Locale locale) {
        utils.validate(result);

        if (repo.existsByEmailAndIdNot(dto.getEmail(), dto.getId())) {
            throw new MyDuplicatedEntryException(
                msgSrc.getMessage("error.duplicatedEntry", new String[]{dto.getEmail()}, locale)
            );
        }

        return repo.save(mapper.mapToEntity(dto));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(int id, Locale locale) {
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Users getById(int id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Users> get(boolean isActive, boolean isBlock, boolean isConnected, String keyword) {
        return repo.findByIsActiveAndIsBlockAndIsConnectedAndUsernameContaining(isActive, isBlock, isConnected, keyword);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Users> getAll() {
        return repo.findAll();
    }
}
