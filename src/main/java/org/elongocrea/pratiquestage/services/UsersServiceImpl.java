package org.elongocrea.pratiquestage.services;

import org.elongocrea.pratiquestage.models.Users;
import org.elongocrea.pratiquestage.repositories.UsersRepo;
import org.elongocrea.pratiquestage.repositories.UsersRepoCustom;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.elongocrea.pratiquestage.utils.dtos.UsersDTO;
import org.elongocrea.pratiquestage.utils.exceptions.MyDuplicatedEntryException;
import org.elongocrea.pratiquestage.utils.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Locale;



import static java.util.Collections.max;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepo repo;

    @Autowired
    private UsersRepoCustom repoC;

    @Autowired
    private UsersMapper mapper;

    @Autowired
    private AppUtils utils;

    @Autowired
    private MessageSource msgSrc;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Users save(UsersDTO dto, BindingResult result, Locale locale) {
        utils.validate(result);

        if (!repoC.isUnique(dto.getId(), dto.getEmail())) {
            throw new MyDuplicatedEntryException(
                    msgSrc.getMessage("error.duplicatedEntry", new String[] { dto.getEmail() }, locale));
        }

        return repo.saveAndFlush(mapper.mapToEntity(dto));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Users> delete(int id, Locale locale) {
        repo.deleteById(id);
        repo.flush();
        return this.get(true, false, true, null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Users getById(int id) {
        return repoC.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Users> get(boolean is_active, boolean is_block, boolean is_connected, String keyword) {
        return repoC.get(is_active, is_block, is_connected, keyword);
    }
}
