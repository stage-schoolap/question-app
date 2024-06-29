package org.elongocrea.pratiquestage.repositories;

import org.elongocrea.pratiquestage.models.People;

import java.util.List;

public interface PeopleRepoCustom {

    boolean isUnique(int id);

    People getById(int id);

    List<People> get(String keyword);
}
