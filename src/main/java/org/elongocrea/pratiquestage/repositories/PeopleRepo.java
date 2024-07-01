package org.elongocrea.pratiquestage.repositories;

import org.elongocrea.pratiquestage.models.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepo extends JpaRepository<People, Integer> {
}
