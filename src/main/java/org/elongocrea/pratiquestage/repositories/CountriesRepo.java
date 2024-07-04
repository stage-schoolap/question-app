package org.elongocrea.pratiquestage.repositories;

import org.elongocrea.pratiquestage.models.Countries;
import org.elongocrea.pratiquestage.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountriesRepo extends JpaRepository<Countries, Integer> {
    boolean existsByNameFrAndIdNot(String nameFr, int id);

    List<Countries> findByNameFrAndNameEnContaining(String nameFr, String keyword);
}
