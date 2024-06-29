package org.elongocrea.pratiquestage.repositories;

import org.apache.commons.lang3.StringUtils;
import org.elongocrea.pratiquestage.models.People;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeopleRepoImpl implements PeopleRepoCustom {

    @Autowired
    private JdbcTemplate jdbcTempl;

    @Autowired
    private AppUtils utils;

    private static final ResultSetExtractorImpl<People> rsExtr = JdbcTemplateMapperFactory.newInstance()
            .addKeys("id", "user_id", "country_id", "city_id")
            .ignorePropertyNotFound()
            .newResultSetExtractor(People.class);

    //
    @Override
    public boolean isUnique(int id) {
        final String sql = "SELECT NOT EXISTS("
                + "SELECT id FROM people WHERE id<>? LIMIT 1"
                + ")";
        final Object[] params = new Object[] {id};

        return  jdbcTempl.queryForObject(sql, Boolean.class, params);
    }

    @Override
    public People getById(int id) {
        final String sql = "SELECT * FROM people WHERE id=?";

        final Object[] params =  new Object[] { id };

        return utils.convertToObject(jdbcTempl.query(sql, rsExtr, params));
    }

    @Override
    public List<People> get(String keyword) {
        final String _stmt = "SELECT * FROM people ";

        final String _order = " ORDER BY id";

        String  sql = _stmt.concat("WHERE firstname LIKE ?").concat(_order);

        final String _keyword = utils.getFilterKeyword(keyword);

        Object[] params = new Object[] { _keyword };

        return jdbcTempl.query(sql, rsExtr, params);
    }
}
