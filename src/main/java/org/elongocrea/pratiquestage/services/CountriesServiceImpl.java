package org.elongocrea.pratiquestage.services;

import lombok.RequiredArgsConstructor;
import org.elongocrea.pratiquestage.models.Countries;
import org.elongocrea.pratiquestage.repositories.CountriesRepo;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.elongocrea.pratiquestage.utils.dtos.CountriesDTO;
import org.elongocrea.pratiquestage.utils.exceptions.MyDuplicatedEntryException;
import org.elongocrea.pratiquestage.utils.mappers.CountriesMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CountriesServiceImpl implements CountriesService{

    private final CountriesRepo repo;
    private final CountriesMapper mapper;
    private final AppUtils utils;
    private final MessageSource msgSrc;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Countries save(CountriesDTO dto, BindingResult result, Locale locale) {
        utils.validate(result);

        if (!repo.existsByNameFrAndIdNot(dto.getNameFr(), dto.getId())) {
            throw new MyDuplicatedEntryException(
                    msgSrc.getMessage("error.duplicatedEntry", new String[]{dto.getNameFr()}, locale)
            );

        }

        return repo.saveAndFlush(mapper.mapToEntity(dto));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(int id, Locale locale) {
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Countries getById(int id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Country not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Countries> get(String nameFr, String keyword) {
        return repo.findByNameFrAndNameEnContaining(nameFr, keyword);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Countries> getAll() {
        return repo.findAll();
    }
}
