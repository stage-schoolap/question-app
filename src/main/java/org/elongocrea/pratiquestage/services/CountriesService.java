package org.elongocrea.pratiquestage.services;

import org.elongocrea.pratiquestage.models.Countries;
import org.elongocrea.pratiquestage.utils.dtos.CountriesDTO;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Locale;

public interface CountriesService {

    Countries save(CountriesDTO dto, BindingResult result, Locale locale);

    List<Countries> delete(int id, Locale locale);

    Countries getById(int id);

    List<Countries> get(String keyword);
}
