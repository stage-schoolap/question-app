package org.elongocrea.pratiquestage.repositories;

import org.elongocrea.pratiquestage.models.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepo extends JpaRepository<Countries, Integer> {

}
