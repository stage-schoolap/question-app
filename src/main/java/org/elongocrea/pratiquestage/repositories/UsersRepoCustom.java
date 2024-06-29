package org.elongocrea.pratiquestage.repositories;

import org.elongocrea.pratiquestage.models.Users;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface UsersRepoCustom {

    boolean isUnique(int id, String email);

    Users getById(int id);

    List<Users> get(boolean is_active, boolean is_block, boolean is_connected, String keyword);
}
