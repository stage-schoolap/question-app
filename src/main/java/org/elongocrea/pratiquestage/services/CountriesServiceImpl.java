package org.elongocrea.pratiquestage.services;

import org.elongocrea.pratiquestage.models.Countries;
import org.elongocrea.pratiquestage.repositories.CountriesRepo;
import org.elongocrea.pratiquestage.repositories.CountriesRepoCustom;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.elongocrea.pratiquestage.utils.dtos.CountriesDTO;
import org.elongocrea.pratiquestage.utils.exceptions.MyDuplicatedEntryException;
import org.elongocrea.pratiquestage.utils.mappers.CountriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Locale;

@Service
public class CountriesServiceImpl implements CountriesService{

    @Autowired
    private CountriesRepo repo;

    @Autowired
    private CountriesRepoCustom repoC;

    @Autowired
    private CountriesMapper mapper;

    @Autowired
    private AppUtils utils;

    @Autowired
    private MessageSource msgSrc;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Countries save(CountriesDTO dto, BindingResult result, Locale locale) {
        utils.validate(result);

        if (!repoC.isUnique(dto.getId(), dto.getName_fr())) {
            throw new MyDuplicatedEntryException(dto.getName_fr());

        }

        return repo.saveAndFlush(mapper.mapToEntity(dto));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Countries> delete(int id, Locale locale) {
        repo.deleteById(id);
        repo.flush();
        return this.get(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Countries getById(int id) {
        return repoC.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Countries> get(String keyword) {
        return repoC.get(keyword);
    }
}
