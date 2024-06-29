package org.elongocrea.pratiquestage.repositories;

import org.elongocrea.pratiquestage.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
}
