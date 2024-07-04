package org.elongocrea.pratiquestage.repositories;

import org.elongocrea.pratiquestage.models.Countries;

import java.util.List;

public interface CountriesRepoCustom {

    boolean isUnique(int id, String name_fr);

    Countries getById(int id);

    List<Countries> get(String keyword);
}
